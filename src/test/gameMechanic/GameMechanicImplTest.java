package gameMechanic;

import base.*;
import dbService.UserDataSet;
import gameClasses.Field;
import gameClasses.Stroke;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.tools.ant.types.resources.First;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static gameClasses.Field.checker.black;
import static gameClasses.Field.checker.white;
import static org.mockito.Matchers.shortThat;
import static org.mockito.Mockito.*;

/**
 * Created by anton on 4/2/14.
 */
public class GameMechanicImplTest {

    private MessageSystem ms;
    private GameSession gameSession;

    @Test
    public void createGamesWithoutUsers() {
        GameMechanicImpl gameMechanic = new GameMechanicImpl(ms);
        Map<String,String> m = new HashMap<String,String>();
        m.put("first", "first value");
        m.put("second", "second value");
        Map<String, UserDataSet> stringUserDataSetHashMap = new HashMap<String,UserDataSet>();
        stringUserDataSetHashMap.put("first", new UserDataSet());
        stringUserDataSetHashMap.put("second", new UserDataSet());
        Map<String, String> h = gameMechanic.createGames(stringUserDataSetHashMap);
        Assert.assertEquals(
                2,
                h.size()
        );

    }

    @Test
    public void createGamesWithoutUsersThree() {
        GameMechanicImpl gameMechanic = new GameMechanicImpl(ms);
        Map<String,String> m = new HashMap<String,String>();
        m.put("first", "first value");
        m.put("second", "second value");
        Map<String, UserDataSet> stringUserDataSetHashMap = new HashMap<String,UserDataSet>();
        stringUserDataSetHashMap.put("first", new UserDataSet());
        stringUserDataSetHashMap.put("second", new UserDataSet());
        stringUserDataSetHashMap.put("third", new UserDataSet());
        Map<String, String> h = gameMechanic.createGames(stringUserDataSetHashMap);
        Assert.assertEquals(
                2,
                h.size()
        );

    }

    @Before
    public void init() {
        ms = mock(MessageSystem.class);
    }

    @Test
    public void createGamesEmptyUsersTest() {
        gameSession = new GameSession(1,2);
        GameMechanicImpl gameMechanic =new GameMechanicImpl(ms);
        Map<String,UserDataSet> users = new HashMap<String, UserDataSet>();
        Map<String,String> sessionIdToColor =  gameMechanic.createGames(users);
        Assert.assertEquals(
                0,
                sessionIdToColor.size()
        );

    }

    @Test
    public void checkStrokeCorrectTest() {
        gameSession = new GameSession(1,2);
        GameMechanicImpl gameMechanic =new GameMechanicImpl(ms);
        Map<String, String> sessionIdToColor = new HashMap<String, String>();
        Map<String, UserDataSet> users = new HashMap<String, UserDataSet>();
        users.put("1", new UserDataSet(1,"op", 2,3,3 ) );
        users.put("2", new UserDataSet(2,"hop", 3,4,5 ) );
        gameMechanic.createGame("1", "2", sessionIdToColor, users);

        Map<Integer,Stroke> resp= gameMechanic.checkStroke(1,new Stroke(7,4,6,5, ""));
        Assert.assertEquals(
                2,
                resp.size()
        );
        Assert.assertEquals(
                "{\"color\":\"\",\"to_x\":7,\"to_y\":4,\"from_x\":6,\"from_y\":5,\"status\":\"true\",\"next\":\"b\"}",
                resp.get(1).toString()
        );
        Assert.assertEquals(
                "{\"color\":\"b\",\"to_x\":0,\"to_y\":3,\"from_x\":1,\"from_y\":2,\"status\":\"true\",\"next\":\"b\"}",
                resp.get(2).toString()
        );

    }

    @Test
    public void checkStrokeIncorrectTest() {
        gameSession = new GameSession(1,2);
        GameMechanicImpl gameMechanic =new GameMechanicImpl(ms);
        Map<String, String> sessionIdToColor = new HashMap<String, String>();
        Map<String, UserDataSet> users = new HashMap<String, UserDataSet>();
        users.put("1", new UserDataSet(1,"op", 2,3,3 ) );
        users.put("2", new UserDataSet(2,"hop", 3,4,5 ) );
        gameMechanic.createGame("1", "2", sessionIdToColor, users);


        Map<Integer,Stroke> resp= gameMechanic.checkStroke(1,new Stroke(1,1,2,2, ""));
        Assert.assertEquals(
                1,
                resp.size()
        );
        Assert.assertEquals(
                "{\"color\":\"\",\"to_x\":2,\"to_y\":2,\"from_x\":1,\"from_y\":1,\"status\":\"false\",\"next\":\"w\"}",
                resp.get(1).toString()
        );

    }

    @Test
    public void checkStrokeNotExistingUserTest() {
        gameSession = new GameSession(1,2);
        GameMechanicImpl gameMechanic =new GameMechanicImpl(ms);
        Map<String, String> sessionIdToColor = new HashMap<String, String>();
        Map<String, UserDataSet> users = new HashMap<String, UserDataSet>();
        users.put("1", new UserDataSet(1,"op", 2,3,3 ) );
        users.put("2", new UserDataSet(2,"hop", 3,4,5 ) );
        gameMechanic.createGame("1", "2", sessionIdToColor, users);

        Map<Integer,Stroke> resp= gameMechanic.checkStroke(3,new Stroke(1,1,2,2, ""));
        Assert.assertEquals(
                0,
                resp.size()
        );
    }

    @Test
    public void o() {
        Field[][] field = new Field[8][8];
        for(int i = 0; i < field.length; i++)
            for(int j = 0; j < field[i].length; j++)
                field[i][j] = new Field(Field.checker.nothing);
        field[6][6].setType(Field.checker.white);

        gameSession = new GameSession(1,2,field);
        GameMechanicImpl gameMechanic =new GameMechanicImpl(ms);
        Map<String, String> sessionIdToColor = new HashMap<String, String>();
        Map<String, UserDataSet> users = new HashMap<String, UserDataSet>();
        users.put("1", new UserDataSet(1,"op", 2,3,3 ) );
        users.put("2", new UserDataSet(2,"hop", 3,4,5 ) );
        gameMechanic.createGame("1", "2", sessionIdToColor, users, field);
        Map<Integer,Stroke> resp= gameMechanic.checkStroke(1,new Stroke(7,0,6,1, ""));
        Assert.assertEquals(
                2,
                resp.size()
        );
        Assert.assertEquals(
                "win",
                resp.get(1).getStatus()
        );
        Assert.assertEquals(
                "lose",
                resp.get(2).getStatus()
        );
    }

    private String print(Field.checker color, Field[][] currentPositions){
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
        System.out.println(result);
        return result;
    }

    @Test
    public void removeDeadGames2Test() throws NoSuchFieldException, IllegalAccessException {
        MessageSystem ms = mock(MessageSystem.class);
        GameMechanicImpl gameMechanic = new GameMechanicImpl(ms);

        Map<Integer,GameSession> userIdToSession= new HashMap<Integer,GameSession>();
        userIdToSession.put(1, null);
        GameSession session = mock(GameSession.class);
        when(session.getWinnerId()).thenReturn(1);
        userIdToSession.put(2, session);

        java.lang.reflect.Field field =  gameMechanic.getClass().getDeclaredField("userIdToSession");
        field.setAccessible(true);
        field.set(gameMechanic,userIdToSession );

        GameMechanicImpl gameMechanicSpy = spy(gameMechanic);

        gameMechanicSpy.removeDeadGames();

        verify(gameMechanicSpy, atLeast(1)).removeDeadGames();
    }
}
