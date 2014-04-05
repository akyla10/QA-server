package gameMechanic;

import gameClasses.Field;
import gameClasses.Snapshot;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by anton on 4/4/14.
 */
public class GameSessionTest {

    GameSession gameSession;

    @Test
    public void GameSessionConstructorDefaultFirstId2paramsTest() {
        gameSession = new GameSession(0, 2);
        Assert.assertEquals(
                gameSession.getId(),
                gameSession.getWhiteId()
        );
    }

    @Test
    public void GameSessionConstructorDefaultSecondId2paramsTest() {
        gameSession = new GameSession(2, 0);
        Assert.assertEquals(
                gameSession.getId(),
                gameSession.getBlackId()
        );
    }

    @Test
    public void GameSessionConstructorDefaultFirstId4paramsTest() {
        gameSession = new GameSession(0, 2, 5,3);
        Assert.assertEquals(
                gameSession.getId(),
                gameSession.getWhiteId()
        );
    }

    @Test
    public void GameSessionConstructorDefaultSecondId4paramsTest() {
        gameSession = new GameSession(2, 0, 4,5);
        Assert.assertEquals(
                gameSession.getId(),
                gameSession.getBlackId()
        );
    }

    @Test
    public void CustomConstructorTest() {
        Field[][] field = new Field[8][8];
        for(int i = 0; i < field.length; i++)
            for(int j = 0; j < field[i].length; j++)
                field[i][j] = new Field(Field.checker.nothing);
        field[2][3].setType(Field.checker.white);
        field[0][7].setType(Field.checker.white);
        field[4][4].setType(Field.checker.white);
        field[4][4].setType(Field.checker.black);
        field[7][0].setType(Field.checker.black);
        field[6][6].setType(Field.checker.black);
        gameSession = new GameSession(1,2,field);
        Assert.assertEquals(
            showField(Field.checker.white),
                "booooooo\n" +
                "oooooobo\n" +
                "oooooooo\n" +
                "oooobooo\n" +
                "oooooooo\n" +
                "ooowoooo\n" +
                "oooooooo\n" +
                "ooooooow\n"
        );
    }

    @Test
    public void getPlayerColorTestBlack() {
        gameSession = new GameSession(2, 4);
        Assert.assertEquals(
                gameSession.getPlayerColor(2),
                Field.checker.white
        );
    }

    @Test
    public void getPlayerColorTestWhite() {
        gameSession = new GameSession(2, 4);
        Assert.assertEquals(
                gameSession.getPlayerColor(4),
                Field.checker.black
        );
    }

    @Test
    public void getAnotherColorTestBlack(){
        gameSession = new GameSession(1, 2, 12,3);
        Field.checker checker = gameSession.getAnotherColor(Field.checker.black);
        Assert.assertTrue(checker == Field.checker.white);
    }

    @Test
    public void getAnotherColorTestWhite(){
        gameSession = new GameSession(1, 2, 12,3);
        Field.checker checker = gameSession.getAnotherColor(Field.checker.white);
        Assert.assertTrue(checker == Field.checker.black);
    }

    @Test
    public void getAnotherColorTestNothing(){
        gameSession = new GameSession(1, 2, 12,3);
        Field.checker checker = gameSession.getAnotherColor(Field.checker.nothing);
        Assert.assertTrue(checker == Field.checker.nothing);
    }

    @Test
    public void getFieldsTest(){
        gameSession = new GameSession(1, 2, 4,2);
        Field.checker checker = gameSession.getAnotherColor(Field.checker.nothing);
        int fields[] = gameSession.getFields();
//        TODO: Запилить тут тест
    }

    @Test
    public void CommonTest(){
        gameSession = new GameSession(1, 2, 8, 3);
                Assert.assertEquals(
                        showField(Field.checker.white),
                        "obobobob\n" +
                        "bobobobo\n" +
                        "obobobob\n" +
                        "oooooooo\n" +
                        "oooooooo\n" +
                        "wowowowo\n" +
                        "owowowow\n" +
                        "wowowowo\n"
                );
        Assert.assertFalse(gameSession.canEat(Field.checker.white));
        Assert.assertFalse(gameSession.canEat(Field.checker.black));
        gameSession.makeUsualStroke(6,2,7,3);
//        print(Field.checker.white);
    }

    @Test
    public void checkingTest() {
        gameSession = new GameSession(1, 2, 8, 3);
        Assert.assertFalse(gameSession.checking(2,6,2,7,3));
        Assert.assertTrue(gameSession.checking(1,6,2,7,3));
        Assert.assertFalse(gameSession.checking(1,6,2,7,4));
      Assert.assertFalse(gameSession.checking(1,5,1,4,0));
    }  

    @Test
    public void checkStrokeTest() {
        gameSession = new GameSession(1, 2, 8, 3);
        /*
        * Black:
            owowowow
            wowowowo
            owowowow
            oooooooo
            oooooooo
            bobobobo
            obobobob
            bobobobo
        *
        * White:
            obobobob
            bobobobo
            obobobob
            oooooooo
            oooooooo
            wowowowo
            owowowow
            wowowowo
        * */
        Assert.assertFalse(gameSession.checkStroke(1,6,2,7,3));
//        Assert.assertFalse(gameSession.checkStroke(1,1,1,2,2));
    }

    String showField(Field.checker color) {
        Field[][] currentPositions = gameSession.getCurrentPositions();
        String result = "";
        if (color == Field.checker.black) {
            for( int i = 0; i < currentPositions.length; i++) {
                for( int j = currentPositions[i].length -1 ; j >=0; j--) {
                    Field field = currentPositions[i][j];
                    if (field.getType() == Field.checker.white) {
                        result += "w";
                    } else if (field.getType() == Field.checker.black) {
                        result += "b";
                    } else if (field.getType() == Field.checker.nothing) {
                        result += "o";
                    }
                }
                result += "\n";
            }
        } else {
            for( int i = currentPositions.length - 1; i >= 0 ; i--) {
                for( int j = 0; j < currentPositions[i].length; j++) {
                    Field field = currentPositions[i][j];
                    if (field.getType() == Field.checker.white) {
                        result += "w";
                    } else if (field.getType() == Field.checker.black) {
                        result += "b";
                    } else if (field.getType() == Field.checker.nothing) {
                        result += "o";
                    }
                }
                result += "\n";
            }
        }
        return result;
    }

    private void print( Field.checker checker) {
        System.out.print(showField(checker));
    }


    private void print( Field.checker checker, Field[][] fields) {
        System.out.print(showField(checker));
    }
}
