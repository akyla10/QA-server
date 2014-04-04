package gameMechanic;

import gameClasses.Field;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by anton on 4/4/14.
 */
public class GameSessionTest {

    GameSession gameSession;
//
//    @Test() {
//
//    }

    @Test
    public void GameSessionConstructorTest2Params() {
        gameSession = new GameSession(1, 2);
        Assert.assertNotNull(gameSession);
    }

    @Test
    public void GameSessionConstructorTest4Params() {
        gameSession = new GameSession(1, 2, 4,3);
        Assert.assertNotNull(gameSession);
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
        System.out.print("");
    }
}
