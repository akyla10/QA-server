package chat;

import base.GameChat;
import base.MessageSystem;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static chat.GameChatImpl.*;
import static frontend.UserDataImpl.putLogInUser;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 29.03.14
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessageTest {
    MessageSystem messageSystem;
    GameChat gameChat;

    @Before
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        gameChat = new GameChatImpl(messageSystem);
    }

    @Test
    public void testSend(){
        gameChat.createChat("1", "2");
        putLogInUser("1", new UserDataSet());
        sendMessage("1", "someMsg");
        assertNotNull(getMessageById("1"));
    }

    @Test
    public void testSendNull(){
        gameChat.createChat("1", "2");
        putLogInUser("1", new UserDataSet());
        sendMessage("1", null);
        assertNull(getMessageById("1"));
    }
}
