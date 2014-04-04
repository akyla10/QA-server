package gameMechanic;

import base.*;
import dbService.UserDataSet;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.mockito.Mockito.mock;

/**
 * Created by anton on 4/2/14.
 */
public class GameMechanicImplTest {

    private MessageSystem ms;

    @Before
    public void init() {
        ms = mock(MessageSystem.class);
    }

    @Test
    public void removeDeadGamesTest() {
        GameMechanicImpl gameMechanic = new GameMechanicImpl(ms);
        Map<String,String> m = new HashMap<String,String>();
        m.put("first", "first value");
        m.put("second", "second value");
        Map<String, UserDataSet> stringUserDataSetHashMap = new HashMap<String,UserDataSet>();
        stringUserDataSetHashMap.put("first", new UserDataSet());
        stringUserDataSetHashMap.put("second", new UserDataSet());
        gameMechanic.createGame("first", "second", m, stringUserDataSetHashMap);
        gameMechanic.removeDeadGames();
//        TODO: допилить тест
    }

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
        System.out.println("");
//        TODO: доплилить тест

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
        System.out.println("");
//        TODO: доплилить тест

    }

    @Test
    public void checkStrokeTest() {

    }


}
