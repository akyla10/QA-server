package frontend;

import base.MessageSystem;
import com.sun.java.swing.plaf.motif.resources.motif;
import com.sun.java.swing.plaf.motif.resources.motif_zh_CN;
import dbService.UserDataSet;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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

//    @Test
//    public void checkUsersTest() throws NoSuchFieldException, IllegalAccessException {
//        MessageSystem ms = mock(MessageSystem.class);
//        UserDataImpl userData1 = new UserDataImpl(ms);
//        UserDataImpl spy = spy(userData1);
//        Map<String, UserDataSet> sessionIdToUserSession = new ConcurrentHashMap<String,UserDataSet>();
//        Field field = spy.getClass().getField("sessionIdToUserSession");
//        field.set(spy, sessionIdToUserSession);
//
//    }
}
