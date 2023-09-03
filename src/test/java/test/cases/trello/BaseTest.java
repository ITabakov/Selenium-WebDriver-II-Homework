package test.cases.trello;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;
import pages.trello.LoginPage;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BaseTest {

    UserActions actions = new UserActions();

    @BeforeClass
    public static void setUp() {

        UserActions.loadBrowser("trello.homePage");

    }

    @AfterClass
    public static void tearDown() {

        UserActions.quitDriver();

    }

    public void login() {

        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");
    }

    public void createBoard() {
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();
    }

    public void createCard() {
        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.addCardToList(getUIMappingByKey("trello.cardName"));
    }

    public void deleteBoard() {
        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.deleteBoard();
    }

    public void clickOnBoard(String boardName) {
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard(boardName);
    }

    public void moveCardToList() throws InterruptedException {
        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.moveCardToList(getUIMappingByKey("trello.cardName"), getUIMappingByKey("trello.listNameDoing"));
    }

    public void assertListToDoExists() {
        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.assertListExists(getUIMappingByKey("trello.listNameToDo"));
    }

    public void assertCardExists(String cardName) {
        String cardXpath = String.format(getUIMappingByKey("trello.boardPage.spanCardName"), cardName);
        actions.waitForElementPresent(cardXpath);
    }

    public void assertCardExistsInList(String listName, String cardName) {

        String cardInListXpath = String.format(getUIMappingByKey("trello.boardPage.cardInListByNames"), listName, cardName);

        actions.waitForElementPresent(cardInListXpath);

    }

    public void assertBoardDeleted() {

        String xpath = getUIMappingByKey("trello.boardXpath");
        String boardName = getUIMappingByKey("trello.boardName");

        Assert.assertFalse(String.format("Board %s not deleted", boardName), actions.isElementPresent(xpath, boardName));

    }

}
