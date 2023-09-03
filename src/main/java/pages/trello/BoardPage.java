package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }


    public void createList(String listName) {
        actions.waitForElementPresent("trello.boardPage.listNameInput");
        actions.typeValueInField(listName, "trello.boardPage.listNameInput");
        actions.clickElement("trello.boardPage.addListButton");
    }

    public void addDefaultListsIfNotPresent() {
        List<WebElement> lists = driver.findElements(By.xpath(getUIMappingByKey("trello.boardPage.listsInBoard")));

        if (lists.size() == 0) {
            BoardPage boardPage = new BoardPage(actions.getDriver());
            boardPage.createDefaultLists();
        }
    }

    public void createDefaultLists() {
        createList(getUIMappingByKey("trello.listNameToDo"));
        createList(getUIMappingByKey("trello.listNameDoing"));
        createList(getUIMappingByKey("trello.listNameDone"));
    }

    public void addCardToList(String cardName) {

        if (!actions.isElementPresent("trello.boardPage.cardNameInput")) {
            actions.waitForElementClickable("trello.boardPage.spanAddCard");
            actions.clickElement("trello.boardPage.spanAddCard");
            actions.waitForElementPresent("trello.boardPage.cardNameInput");
        }
        actions.typeValueInField(cardName, "trello.boardPage.cardNameInput");
        actions.clickElement("trello.boardPage.addCardButton");
    }

    public void moveCardToList(String cardName, String listName) throws InterruptedException {

        Actions actionDragDrop = new Actions(driver);
        String sourceXpath = String.format(getUIMappingByKey("trello.boardPage.spanCardName"), cardName);
        actions.waitForElementPresent(sourceXpath);
        WebElement source = driver.findElement(By.xpath(sourceXpath));
        String targetXpath = String.format(getUIMappingByKey("trello.boardPage.textAreaListName"), listName);
        actions.waitForElementPresent(targetXpath);
        WebElement target = driver.findElement(By.xpath(targetXpath));
        actionDragDrop.dragAndDrop(source, target).build().perform();
        actions.waitFor(1000);

    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertAddListExists() {
        actions.waitForElementPresent("trello.boardPage.listWrapper");
    }

    public void deleteBoard() {

        //clickOnShowMenuButton
        actions.waitForElementClickable("trello.boardPage.showMenuButton");
        actions.clickElement("trello.boardPage.showMenuButton");

        // clickOnCloseBoardButton
        actions.waitForElementClickable("trello.boardPage.closeBoardButton");
        actions.clickElement("trello.boardPage.closeBoardButton");

        // clickOnCloseButton
        actions.waitForElementClickable("trello.boardPage.closeButton");
        actions.clickElement("trello.boardPage.closeButton");

        // clickOnPermanentlyDeleteButton
        actions.waitForElementClickable("trello.boardPage.permanentlyDeleteButton");
        actions.clickElement("trello.boardPage.permanentlyDeleteButton");

        // clickOnPermanentlyDeleteConfirmButton
        actions.waitForElementClickable("trello.boardPage.permanentlyDeleteConfirmButton");
        actions.clickElement("trello.boardPage.permanentlyDeleteConfirmButton");
    }

}
