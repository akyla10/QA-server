package dbServiceTest;

import base.MessageSystem;
import dbService.DBServiceImpl;
import dbService.TExecutor;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 07.04.14
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class TExecutorTest {
    DBServiceImpl dbService;
    MessageSystem ms;
    Connection connection;
    TExecutor exec;

    @Before
    public void setUp() throws Exception {
        ms = new MessageSystemImpl();
        dbService = new DBServiceImpl(ms);

        connection = dbService.setConnection();
        Assert.assertNotNull(dbService);
        Assert.assertNotNull(connection);

    }

    @Test
    public void testExec() throws Exception {
        exec.addUser(connection, "login9", "password9");
        Assert.assertNotNull(exec.findUser(connection, "login9"));
        dbService.removeUserByLogin("login9");

    }

    @Test
    public void testFindUser() throws Exception {
        exec.addUser(connection, "login8", "password8");
        Assert.assertEquals(TExecutor.findUser(connection, "login8"), 1);
        Assert.assertEquals(TExecutor.findUser(connection, "notExistingUser"), 0);
        Assert.assertEquals(TExecutor.findUser(connection, null), 0);

        dbService.removeUserByLogin("login8");

    }
}
