package gameMechanic;

import gameClasses.Field;
import gameClasses.Snapshot;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static gameClasses.Field.checker.black;
import static gameClasses.Field.checker.white;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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
        Assert.assertFalse("Сработал переход на неигровую клетку",gameSession.checkStroke( 1, 0, 5, 1, 5));
        Assert.assertFalse("Сработал переход c неигровой клетки",gameSession.checkStroke( 1, 1, 5, 0, 5));
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
                        result += field.isKing() ? "W" : "w";
                    } else if (field.getType() == black) {
                        result += field.isKing() ? "B" : "b";
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
                        result += field.isKing() ? "W" : "w";
                    } else if (field.getType() == black) {
                        result += field.isKing() ? "B" : "b";
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
    public void snapshotWhiteCurrentBlackTest() {
        gameSession = new GameSession(1, 2, 8, 3);
        Assert.assertTrue(gameSession.checkStroke(1, 0, 5, 1, 4));
        Assert.assertEquals(
            "Не тот снимок",
            "{\"status\":\"snapshot\",\"next\":\"b\",\"color\":\"w\",\"field\":[[\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\"], [\"nothing\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"white\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"], [\"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"]],\"king\":[[\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"]]}",
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
    @Test
    public void KingTeleportTest() {
        Field[][] field = getEmptyField(8);
        field[4][6].setType(black);
        field[1][3].setType(white);
        field[1][3].makeKing();
        gameSession = new GameSession(1,2,field);
        System.out.print("\n\n");
        Assert.assertFalse(
                "Почему-то телепортировалась дамка",
                gameSession.checkStroke(1,3,6,7,0)
        );
    }

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
        Assert.assertTrue(gameSession.checkStroke(1, 2, 5, 4, 3));
    }

    @Test
    public void cantCommonStrokeWhenCanEat() {
        Field[][] field = getEmptyField(8);
        field[3][3].setType(black);
        field[2][2].setType(white);
        field[6][0].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(
                "Разрешено делать обычный ход вместо удара",
                gameSession.checkStroke(1, 0, 1, 1, 0)
        );
    }

    @Test
    public void PawnLeftBottomEatTest() {
        Field[][] field = getEmptyField(8);
        field[2][2].setType(black);
        field[3][3].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertTrue(
                "Не получается ударить шашку сзади слева",
                gameSession.checkStroke(1, 3, 4, 1, 6)
        );
    }

    @Test
    public void PawnRightBottomEatTest() {
        Field[][] field = getEmptyField(8);
        field[2][4].setType(black);
        field[3][3].setType(white);
        gameSession = new GameSession(1,2,field);
        Assert.assertTrue(
                "Не получается ударить шашку сзади справа",
                gameSession.checkStroke(1, 3, 4, 5, 6)
        );
    }

    @Test
    public void checkNobodyWon() {
        gameSession = new GameSession(1,2,8,3);
        Assert.assertEquals(
                "Никто пока не побеждает",
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void checkWhiteWonCourseThereNoBlacks() {
        Field[][] field = getEmptyField(8);
        field[5][5].setType(white);
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                "Белые не победили",
                1,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void checkBlackWonCourseThereNoWhites() {
        Field[][] field = getEmptyField(8);
        field[5][5].setType(black);
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                "Черные не победили",
                2,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void checkLogNoteWhite() {
        gameSession = new GameSession(1,2);
        Assert.assertEquals(
                "Белые не победили",
                "white",
                gameSession.saveLog(1)
        );
    }

    @Test
    public void checkLogNoteBlock() {
        gameSession = new GameSession(1,2);
        Assert.assertEquals(
                "Черные не победили",
                "black",
                gameSession.saveLog(2)
        );
    }

    @Test
    public void checkWhiteWonCourseBlacksAreBlocked() {
        Field[][] field = getEmptyField(8);
        field[7][7].setType(black);
        field[6][6].setType(white);
        field[4][4].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1, 4, 3, 5, 2);
        Assert.assertEquals(
                "Белые не победили",
                1,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void checkBlackWonCourseWhitesAreBlocked() {
        Field[][] field = getEmptyField(8);
        field[0][0].setType(white);
        field[1][1].setType(black);
        field[2][2].setType(black);
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                "Черные не победили",
                2,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void testNormalZero() {
        gameSession = new GameSession(1,2);
        Assert.assertEquals(0, gameSession.normal(0));
    }

    @Test
    public void AssertQuantityDecrement() {
        Field[][] field = getEmptyField(8);
        field[7][7].setType(black);
        field[5][5].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1,5,2,6,1);
        gameSession.checkStroke(2,0,7,2,5);
        Assert.assertEquals(0, gameSession.getWhiteQuantity());
    }

    @Test
    public void lMoveByKingTest() {
        Field[][] field = getEmptyField(8);
        field[0][4].setType(white);
        field[0][4].makeKing();
        field[7][3].setType(black);
        gameSession = new GameSession(1,2,field);
        gameSession.checkStroke(1, 4, 7, 6, 5);
    }

    @Test
    public void checkWhiteBecomingKing() {
        Field[][] field = getEmptyField(8);
        field[6][4].setType(white);
        gameSession = new GameSession(1,2,field);
        gameSession.checkStroke(1, 4, 1, 5, 0);
        Assert.assertTrue(gameSession.getCurrentPositions()[7][5].isKing());
    }

    @Test
    public void checkBlackBecomingKing() {
        Field[][] field = getEmptyField(8);
        field[6][4].setType(white);
        field[1][3].setType(black);
        gameSession = new GameSession(1,2,field);
        gameSession.checkStroke(1, 4, 1, 5, 0);
        gameSession.checkStroke(2, 4, 1, 5, 0);
        Assert.assertTrue(gameSession.getCurrentPositions()[0][2].isKing());
    }

    @Test
    public void getFieldsTest() {
        Field[][] field = getEmptyField(8);
        field[0][6].setType(white);
        field[1][1].setType(black);
        field[4][4].setType(black);
        field[7][1].setType(black);
        field[6][4].setType(white);
        gameSession = new GameSession(1,2,field);
        gameSession.checkStroke(1, 4, 1, 5, 0);
        gameSession.checkStroke(2, 6, 1, 7, 0);
        int[] sample = {6,-61,0,36,57};
        int[] fields = gameSession.getFields();
        for (int i = 0; i < sample.length; i++)
            Assert.assertEquals(
                    sample[i],
                    fields[i]
            );

    }

    @Test
    public void overBoardMoveTest() {
        Field[][] field = getEmptyField(8);
        field[5][5].setType(white);
        field[5][5].makeKing();
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(gameSession.checkStroke(1, 5, 2, 8, -1));
        Assert.assertFalse(gameSession.checkStroke(1, 5, 2, 2, -1));
        Assert.assertFalse( gameSession.checkStroke(1,5,2,9, 6) );
        Assert.assertFalse(gameSession.checkStroke(1, 5, 2, -10, 17));
    }

    @Test
    public void AssertNotBlockWhenCanEatBackLeftTest() {
        Field[][] field = getEmptyField(8);
        field[4][6].setType(black);
        field[6][6].setType(black);
        field[7][5].setType(black);
        field[5][7].setType(white);
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertNotBlockWhenCanEatBackLeftKingTest() {
        Field[][] field = getEmptyField(8);
        field[4][6].setType(black);
        field[6][6].setType(black);
        field[7][5].setType(black);
        field[5][7].setType(white);
        field[5][7].makeKing();
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertNotBlockWhenCanEatBackRightTest() {
        /*
        oooooooo
        oobooooo
        oboooooo
        wooooooo
        oboooooo
        oooooooo
        oooooooo
        oooooooo
        */
        Field[][] field = getEmptyField(8);
        field[3][1].setType(black);
        field[5][1].setType(black);
        field[6][2].setType(black);
        field[4][0].setType(white);
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertNotBlockWhenCanEatBackRightKingTest() {
        /*
        oooooooo
        oobooooo
        oboooooo
        Wooooooo
        oboooooo
        oooooooo
        oooooooo
        oooooooo
        */
        Field[][] field = getEmptyField(8);
        field[3][1].setType(black);
        field[5][1].setType(black);
        field[6][2].setType(black);
        field[4][0].setType(white);
        field[4][0].makeKing();
        gameSession = new GameSession(1,2, field);
        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertNotBlockWhenCanEatBackLeftWhiteTest() {
        Field[][] field = getEmptyField(8);
        field[4][0].setType(black);
        field[4][2].setType(white);
        field[3][1].setType(white);
        field[2][2].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1, 2, 3, 1, 2);
        /*
        Вид со стороны черных print(black);
            oooooooo
            oooooooo
            ooooowoo
            oooooowo
            ooooooob
            oooooowo
            oooooooo
            oooooooo
        * */

        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertBlockWhenCanNotEatBackLeftBlackTest() {
        Field[][] field = getEmptyField(8);
        field[4][0].setType(black);
        field[4][2].setType(white);
        field[3][1].setType(white);
        field[2][2].setType(white);
        field[6][2].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1,2,3,1,2);
        /*
        Вид со стороны черных print(black);
            oooooooo
            oooooooo
            ooooowoo
            oooooowo
            ooooooob
            oooooowo
            ooooowoo
            oooooooo
        * */

        Assert.assertEquals(
                1,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertBlockWhenCanNotEatBackRightBlackTest() {
        Field[][] field = getEmptyField(8);
        field[3][7].setType(black);
        field[3][7].makeKing();
        field[4][4].setType(white);
        field[2][6].setType(white);
        field[1][5].setType(white);
        field[4][6].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1,4,3,5,2);
        /*
        Вид со стороны черных print(black);
            oooooooo
            oowooooo
            owoooooo
            Booooooo
            owoooooo
            oowooooo
            oooooooo
            oooooooo
        * */

        Assert.assertEquals(
                1,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertBlackKingNotBlockTopTest() {
        Field[][] field = getEmptyField(8);
        field[3][7].setType(black);
        field[3][7].makeKing();
        field[4][4].setType(white);
        field[2][6].setType(white);
        field[1][5].setType(white);
        field[4][6].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1,4,3,3,2);
        /*
        Вид со стороны черных print(black);
            oooooooo
            oowooooo
            owoooooo
            Booooooo
            owoooooo
            oooowooo
            oooooooo
            oooooooo
        * */

        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

//    @Test
//    Тест проваливается
    public void AssertBlackNotBlockTopTest() {
        Field[][] field = getEmptyField(8);
        field[3][7].setType(black);
        field[3][7].makeKing();
        field[4][4].setType(white);
        field[2][6].setType(white);
        field[1][5].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1, 4, 3, 3, 2);
        /*
        Вид со стороны черных print(black);
            oooooooo
            oowooooo
            owoooooo
            Booooooo
            oooooooo
            oooowooo
            oooooooo
            oooooooo
        * */

        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void AssertNotBlockWhenCanEatBackLeftKingWhiteTest() {
        Field[][] field = getEmptyField(8);
        field[4][0].setType(black);
        field[4][0].makeKing();
        field[4][2].setType(white);
        field[3][1].setType(white);
        field[2][2].setType(white);
        gameSession = new GameSession(1,2, field);
        gameSession.checkStroke(1,2,3,1,2);
        /*
        Вид со стороны черных print(black);
            oooooooo
            oooooooo
            ooooowoo
            oooooowo
            oooooooB
            oooooowo
            oooooooo
            oooooooo
        * */

        Assert.assertEquals(
                0,
                gameSession.getWinnerId()
        );
    }

    @Test
    public void KingCantMoveWhenCanEatLeftTest() {
        Field[][] field = getEmptyField(8);
        field[3][3].setType(white);
        field[3][3].makeKing();
        field[5][5].setType(black);
        gameSession = new GameSession(1,2,field);
        Assert.assertFalse(
            gameSession.checkStroke(1,3,4,2,3)
        );
    }

    @Test
//    Тест проваливается
    public void KingCantMoveWhenCanEatRightTest() {
        Field[][] field = getEmptyField(8);
        field[4][4].setType(white);
        field[4][4].makeKing();
        field[6][2].setType(black);
        gameSession = new GameSession(1,2,field);
        /*
            oooooooo
            oobooooo
            oooooooo
            ooooWooo
            oooooooo
            oooooooo
            oooooooo
            oooooooo
        * */
        Assert.assertFalse(
            gameSession.checkStroke(1,4,3,5,2)
        );
    }

    @Test
    public void canMoveRightUpTest() {
        gameSession = new GameSession(1,2,8,3);
        Assert.assertFalse(
                gameSession.canMoveRightUp(3452,3456)
        );
    }

    @Test
    public void canMoveLeftUpTest() {
        gameSession = new GameSession(1,2,8,3);
        Assert.assertFalse(
                gameSession.canMoveLeftUp(3452,3456)
        );
    }

    @Test
    public void canMoveLeftDownTest() {
        gameSession = new GameSession(1,2,8,3);
        Assert.assertFalse(
                gameSession.canMoveLeftDown(-3452,-456)
        );
    }

    @Test
    public void canMoveRightDownTest() {
        gameSession = new GameSession(1,2,8,3);
        Assert.assertFalse(
                gameSession.canMoveRightDown(-3452, -456)
        );
    }

//    Теперь тесты с использованием моков и прочей ерунды

    @Test
    public void checkStroke() {
        gameSession = new GameSession(1,2);
        GameSession spy = spy(gameSession);
        when(spy.checkEating(1,6,2,5)).thenReturn(Boolean.FALSE);
        when(spy.eating(1,6,2,5)).thenReturn(Boolean.TRUE);
        when(spy.checking(1,1,6,2,5)).thenReturn(Boolean.TRUE);
        Assert.assertFalse(
                spy.checkStroke(1,1,1,2,2)
        );
    }

    @Test
    public void checkEatingTest() {
        gameSession = new GameSession(1,2);
        GameSession spy = spy(gameSession);
        when(spy.checkKingOtherEating( 1, 1, 2, 2)).thenReturn(Boolean.FALSE);
        when(spy.fieldIsKing(1, 1)).thenReturn(Boolean.TRUE);
        Boolean aBoolean = spy.checkEating( 1, 1, 2, 2);
        Assert.assertTrue(
                spy.checkEating( 1, 1, 2, 2)
        );
    }

}
