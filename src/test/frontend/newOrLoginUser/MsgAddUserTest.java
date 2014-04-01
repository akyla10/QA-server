package frontend.newOrLoginUser;

import base.*;
import dbService.UserDataSet;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anton on 4/1/14.
 */
public class MsgAddUserTest {

    @Test
    public void execTestExist() {
        DataAccessObjectImpl dataAccessObject = new DataAccessObjectImpl();
        MsgAddUser msgAddUser = new MsgAddUser(null, null, "", "true", "" );
        UserDataSet userDataSet =  msgAddUser.exec(dataAccessObject);
        Assert.assertNotNull(userDataSet);
    }

    @Test
    public void execTestNotExist() {
        DataAccessObjectImpl dataAccessObject = new DataAccessObjectImpl();
        MsgAddUser msgAddUser = new MsgAddUser(null, null, "", "true1", "" );
        UserDataSet userDataSet =  msgAddUser.exec(dataAccessObject);
        Assert.assertNull(userDataSet);
    }



    private class DataAccessObjectImpl implements DataAccessObject {

        @Override
        public MessageSystem getMessageSystem() {
            MSImpl ms = new MSImpl();
            return ms;
        }

        @Override
        public UserDataSet getUDS(String login, String password) {
            UserDataSet userDataSet = new UserDataSet();
            return userDataSet;
        }

        @Override
        public boolean addUDS(String login, String password) {
            if (login.equals("true")) {
                return true;
            }
            return false;
        }

        @Override
        public void updateUsers(List<UserDataSet> users) {

        }

        @Override
        public Address getAddress() {
            Address address = new Address();
            return null;
        }

        @Override
        public void run() {

        }

        public class MSImpl implements MessageSystem {
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

            ;
        }

    }
}
