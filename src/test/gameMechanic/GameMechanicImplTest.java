package gameMechanic;

import base.*;
import dbService.UserDataSet;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anton on 4/2/14.
 */
public class GameMechanicImplTest {

    @Test
    public void removeDeadGamesTest() {
        MS ms = new MS();
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
    public void createGamesWhithoutUsers() {
        MS ms = new MS();
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
    public void createGamesWhithoutUsersThree() {
        MS ms = new MS();
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

    public class MS implements MessageSystem {

        @Override
        public void addService(Abonent abonent, String name) {

        }

        @Override
        public Address getAddressByName(String name) {
            return null;
        }

        @Override
        public Map<Address, ConcurrentLinkedQueue<Msg>> getMessages() {
            return null;
        }

        @Override
        public void putMsg(Address to, Msg msg) {

        }

        @Override
        public void execForAbonent(Abonent abonent) {

        }
    }

}
