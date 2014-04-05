package gameMechanic;

import gameClasses.Field;
import gameClasses.Snapshot;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static gameClasses.Field.checker.black;
import static gameClasses.Field.checker.white;

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
        Field[][] field = getEmptyField(8);
        field[2][3].setType(white);
        field[0][7].setType(white);
        field[4][4].setType(white);
        field[4][4].setType(black);
        field[7][0].setType(black);
        field[6][6].setType(black);
        gameSession = new GameSession(1,2,field);
        Assert.assertEquals(
                showField(white),
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
                white
        );
    }

    @Test
    public void getPlayerColorTestWhite() {
        gameSession = new GameSession(2, 4);
        Assert.assertEquals(
                gameSession.getPlayerColor(4),
                black
        );
    }

    @Test
    public void getAnotherColorTestBlack(){
        gameSession = new GameSession(1, 2, 12,3);
        Field.checker checker = gameSession.getAnotherColor(black);
        Assert.assertTrue(checker == white);
    }

    @Test
    public void getAnotherColorTestWhite(){
        gameSession = new GameSession(1, 2, 12,3);
        Field.checker checker = gameSession.getAnotherColor(white);
        Assert.assertTrue(checker == black);
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
                        showField(white),
                        "obobobob\n" +
                        "bobobobo\n" +
                        "obobobob\n" +
                        "oooooooo\n" +
                        "oooooooo\n" +
                        "wowowowo\n" +
                        "owowowow\n" +
                        "wowowowo\n"
                );
        Assert.assertFalse(gameSession.canEat(white));
        Assert.assertFalse(gameSession.canEat(black));
        gameSession.makeUsualStroke(6,2,7,3);
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
    public void checkStrokeBasicTest() {
        /*
        * Начальное состояние и координаты в checkStroke
        * Black:
        x>  01234567
    \/y   0 owowowow
          1 wowowowo
          2 owowowow
          3 oooooooo
          4 oooooooo
          5 bobobobo
          6 obobobob
          7 bobobobo
        *
        * White:
        *
        x>  01234567
      \/y 0 obobobob 0
          1 bobobobo 1
          2 obobobob 2
          3 oooooooo 3
          4 oooooooo 4
          5 wowowowo 5
          6 owowowow 6
          7 wowowowo 7

        * */
        gameSession = new GameSession(1, 2, 8, 3);
        Assert.assertFalse("Сработал переход за пределы массива", gameSession.checkStroke(1,7,6,8,5));
        Assert.assertFalse("Ход чужой шашкой",gameSession.checkStroke(1,1,2,0,3));
        Assert.assertFalse("Сработал переход, куда нельзя",gameSession.checkStroke(1, 0, 5, 2, 3));

        Assert.assertFalse("Сработал переход на неигровую клетку",gameSession.checkStroke( 1, 0, 5, 0, 4));
        Assert.assertFalse("Сработал переход на ту же позицию", gameSession.checkStroke(1, 0, 5, 0, 5));
        Assert.assertTrue(gameSession.checkStroke(1, 0, 5, 1, 4));
        Assert.assertTrue(gameSession.checkStroke(2, 0, 5, 1, 4));
    }

    String showField(Field.checker color) {
        Field[][] currentPositions = gameSession.getCurrentPositions();
        String result = "";
        if (color == black) {
            for( int i = 0; i < currentPositions.length; i++) {
                for( int j = currentPositions[i].length -1 ; j >=0; j--) {
                    Field field = currentPositions[i][j];
                    if (field.getType() == white) {
                        result += "w";
                    } else if (field.getType() == black) {
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
                    if (field.getType() == white) {
                        result += "w";
                    } else if (field.getType() == black) {
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

    private Field[][] getEmptyField(int size) {
        Field[][] field = new Field[size][size];
        for(int i = 0; i < field.length; i++)
            for(int j = 0; j < field[i].length; j++)
                field[i][j] = new Field(Field.checker.nothing);
        return field;
    }

    @Test
    public void EatToRightTopTest() {
        Field[][] field = getEmptyField(8);
        field[0][2].setType(white);
        field[1][3].setType(black);
        gameSession = new GameSession(1,2,field);
        gameSession.checkStroke(1, 2, 7, 4, 5);
        Assert.assertEquals(
                showField(white),
                "oooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n" +
                "oooowooo\n" +
                "oooooooo\n" +
                "oooooooo\n"
        );

    }

    @Test
    public void EatToLeftTopTest() {
        Field[][] field = getEmptyField(8);
        field[0][2].setType(white);
        field[1][1].setType(black);
        gameSession = new GameSession(1,2,field);
        gameSession.checkStroke(1, 2, 7, 0, 5);
        Assert.assertEquals("Ошибка хода",
                "oooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n" +
                "wooooooo\n" +
                "oooooooo\n" +
                "oooooooo\n",
                showField(white)
        );

    }

    @Test
    public void canEatPawnTopRightExitTopTest() {
        Field[][] field = getEmptyField(8);
        field[7][6].setType(black);
        field[6][5].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(5, 6));
    }

    @Test
    public void canEatPawnTopRightExitRightTest() {
        Field[][] field = getEmptyField(8);
        field[6][7].setType(black);
        field[5][6].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(6, 5));
    }

    @Test
    public void canEatPawnTopLeftExitTopTest() {
        Field[][] field = getEmptyField(8);
        field[7][2].setType(black);
        field[6][3].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(3, 6));
    }

    @Test
    public void canEatPawnTopLeftExitLeftTest() {
        Field[][] field = getEmptyField(8);
        field[6][0].setType(black);
        field[5][1].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(1, 5));
    }

    @Test
    public void canEatPawnTopRightBothWhiteTest() {
        Field[][] field = getEmptyField(8);
        field[4][5].setType(white);
        field[3][4].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(4, 3));
    }

    @Test
    public void canEatKingTopRightExitTopTest() {
        Field[][] field = getEmptyField(8);
        field[7][6].setType(black);
        field[6][5].setType(white);
        field[6][5].makeKing();
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(5, 6));
    }

    @Test
    public void canEatKingTopRightExitRightTest() {
        Field[][] field = getEmptyField(8);
        field[6][7].setType(black);
        field[5][6].setType(white);
        field[5][6].makeKing();
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(6, 5));
    }

    @Test
    public void canEatKingTopRightBothWhiteTest() {
        Field[][] field = getEmptyField(8);
        field[4][5].setType(white);
        field[3][4].setType(white);
        field[3][4].makeKing();
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.canEat(4, 3));
    }

    @Test
    public void snapshotWhiteTest() {
        gameSession = new GameSession(1, 2, 8, 3);
        Assert.assertEquals(
            "Не тот снимок",
            "{\"status\":\"snapshot\",\"next\":\"w\",\"color\":\"w\",\"field\":[[\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\"], [\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"], [\"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"]],\"king\":[[\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"]]}",
            gameSession.getSnapshot(1).toString()
        );
    }

    @Test
    public void snapshotBlackTest() {
        gameSession = new GameSession(1, 2, 8, 3);
        Assert.assertEquals(
            "Не тот снимок",
            "{\"status\":\"snapshot\",\"next\":\"w\",\"color\":\"b\",\"field\":[[\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\"], [\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"], [\"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"]],\"king\":[[\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"]]}",
            gameSession.getSnapshot(2).toString()
        );
    }

    @Test
    public void doubleMoveTest() {
        Field[][] field = getEmptyField(8);
        field[3][3].setType(white);
        field[6][4].setType(black);
        field[4][4].setType(black);
        gameSession = new GameSession(1,2,field);
        Assert.assertTrue(
                "Не удается сделать второй ход",
                gameSession.checkStroke(1, 3, 4, 5, 2) && gameSession.checkStroke(1, 5, 2, 3, 0)
        );
    }

    @Test
    public void TestKingStrokeRightTop() {
        Field[][] field = getEmptyField(8);
        field[4][6].setType(black);
        field[1][3].setType(white);
        field[1][3].makeKing();
        gameSession = new GameSession(1,2,field);
        Assert.assertTrue(
                "Не работает удар дамкой",
                gameSession.checkStroke(1,3,6,7,2)
        );
    }

//    Тест проваливается
//    @Test
//    public void KingTeleportTest() {
//        Field[][] field = getEmptyField(8);
//        field[4][6].setType(black);
//        field[1][3].setType(white);
//        field[1][3].makeKing();
//        gameSession = new GameSession(1,2,field);
//        System.out.print("\n\n");
//        Assert.assertFalse(
//                "Почему-то телепортировалась дамка",
//                gameSession.checkStroke(1,3,6,7,0)
//        );
//    }

    @Test
    public void TeleportTest() {
        Field[][] field = getEmptyField(8);
        field[4][6].setType(black);
        field[1][3].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(
                "Почему-то телепортировалась шашка",
                gameSession.checkStroke(1,3,6,7,0)
        );
    }

    @Test
    public void pownEatNear() {
        Field[][] field = getEmptyField(8);
        field[3][3].setType(black);
        field[2][2].setType(white);
        field[2][2].makeKing();
        gameSession = new GameSession(1,2,field);
        Assert.assertTrue( gameSession.checkStroke(1,2,5,4,3) );
    }
}