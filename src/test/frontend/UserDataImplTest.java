package frontend;

import base.MessageSystem;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 06.04.14
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */

public class UserDataImplTest {
    MessageSystem messageSystem;
    UserDataImpl userData;

    @Before
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        userData = new UserDataImpl(messageSystem);


    }

    @Test
    public void testGetAddress() throws Exception {
        Assert.assertNotNull(userData.getAddress());
    }

    @Test
    public void testGetStartServerTime() throws Exception {
        Assert.assertNotNull(UserDataImpl.getStartServerTime());
    }

    @Test
    public void testStart() throws Exception {
        Thread th = new Thread(this.userData);
        th.start();
        try { th.sleep(0); }
        catch (InterruptedException e) {}

    }
}
