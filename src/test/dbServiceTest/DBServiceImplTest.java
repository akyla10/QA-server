package dbServiceTest;

import base.MessageSystem;
import dbService.DBServiceImpl;
import dbService.UserDataSet;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 05.04.14
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class DBServiceImplTest {
    DBServiceImpl dbService;
    MessageSystem ms;
    Connection connection;

    @Before
    public void setUp() throws Exception {
        ms = new MessageSystemImpl();
        dbService = new DBServiceImpl(ms);

        connection = dbService.setConnection();
        Assert.assertNotNull(dbService);
        Assert.assertNotNull(connection);

        dbService.addUDS("login1", "pass1");

    }

    @Test
    public void testGetMethods() throws Exception {
        Assert.assertNotNull(dbService.getMessageSystem());
        Assert.assertNotNull(dbService.getAddress());
    }

    @Test
    public void testGetUDS() throws Exception {
        UserDataSet uds = dbService.getUDS("login1", "pass1");
        Assert.assertNotNull(uds);
        Assert.assertEquals("login1", uds.getNick());

        // non-existing uds
        UserDataSet udsEmpty = dbService.getUDS("qweqweqwe", "123");
        Assert.assertNull(udsEmpty);
    }

    @Test
    public void testAddExistingUDS() throws Exception {
        boolean addUDSResult = dbService.addUDS("login11", "pass11");
        Assert.assertTrue(addUDSResult);
        Assert.assertFalse(dbService.addUDS("login11", "pass11"));
        dbService.removeUserByLogin("login11");
    }

    @Test
    public void testUpdateUDS() throws Exception {
        dbService.addUDS("login2", "pass2");
        UserDataSet newUds = new UserDataSet(dbService.getUDS("login2", "pass2").getId(), "newLogin", 1, 1, 1);
        List<UserDataSet> userList = new Vector<UserDataSet>();
        userList.add(newUds);
        dbService.updateUsers(userList);


        Assert.assertNull((dbService.getUDS("newLogin", "pass2")));
        Assert.assertEquals("login2", dbService.getUDS("login2", "pass2").getNick());
    }

    @Test
    public void testThread() throws Exception {
        Thread dbThread = new Thread(this.dbService);
        dbThread.start();

        dbThread.interrupt();
    }

}
