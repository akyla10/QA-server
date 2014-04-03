package frontend.newOrLoginUser;

import base.*;
import dbService.UserDataSet;
import junit.framework.Assert;
import junit.framework.TestCase;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 4/1/14.
 */
public class MsgAddUserTest {

    private DataAccessObject dataAccessObject;
    private MessageSystem messageSystem;

    @Before
    public void init() {
        dataAccessObject = mock(DataAccessObject.class);
        when(dataAccessObject.addUDS("true", "")).thenReturn(true);
        when(dataAccessObject.addUDS("false", "")).thenReturn(false);
        messageSystem = mock(MessageSystem.class);
        when(dataAccessObject.getMessageSystem()).thenReturn(messageSystem);
        when(dataAccessObject.getUDS("true","")).thenReturn(new UserDataSet());
    }

    @Test
    public void execTestExist() {
        MsgAddUser msgAddUser = new MsgAddUser(null, null, "", "true", "" );
        UserDataSet userDataSet =  msgAddUser.exec(dataAccessObject);
        Assert.assertNotNull(userDataSet);
    }

    @Test
    public void execTestNotExist() {
        MsgAddUser msgAddUser = new MsgAddUser(null, null, "", "false", "" );
        UserDataSet userDataSet =  msgAddUser.exec(dataAccessObject);
        Assert.assertNull(userDataSet);
    }
}
