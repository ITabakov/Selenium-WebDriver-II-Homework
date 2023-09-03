package test.cases.trello;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.telerikacademy.testframework.Utils.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardTest extends BaseTest {

    @Test
    public void T1_createBoardWhenCreateBoardClicked() {

        login();

        createBoard();

        assertListToDoExists();
    }

    @Test
    public void T2_createNewCardInExistingBoardWhenCreateCardClicked() {

        createCard();
        String cardName = getUIMappingByKey("trello.cardName");
        assertCardExists(cardName);

    }

    @Test
    public void T3_moveCardBetweenStatesWhenDragAndDropIsUsed() throws InterruptedException {

        moveCardToList();

        String listName = getUIMappingByKey("trello.listNameDoing");
        String cardName = getUIMappingByKey("trello.cardName");

        assertCardExistsInList(listName, cardName);

    }

    @Test
    public void T4_deleteBoardWhenDeleteButtonIsClicked() {

        deleteBoard();

        assertBoardDeleted();

    }
}
