package chat;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static frontend.UserDataImpl.getStartServerTime;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 08.04.14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */



// no asserts

public class ChatWSImplTest {
    ChatWSImpl chat;
    ChatWSImpl chatSpy;

    @Before
    public void setUp() throws Exception {
        chat = new ChatWSImpl();
        chatSpy = spy(new ChatWSImpl());

        when(chatSpy.isNotConnected()).thenReturn(false);

        Assert.assertNotNull(chat);
        Assert.assertNotNull(chatSpy);

    }

    @Test
    public void testNotJson(){
        chat.onWebSocketText("notJson");
        chatSpy.onWebSocketText("notJson");
    }

    @Test
    public void testJSON(){
        chatSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"" + getStartServerTime() + "\", \"text\":\"test msg\"}");
        chatSpy.onWebSocketText("{\"sessionId\":\"null\", \"startServerTime\":\"null\", \"text\":\"\"}");
        chatSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"" +  getStartServerTime()  + "\"}");
        chatSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"0\", \"text\":\"null\"}");
        chatSpy.onWebSocketText("{\"sessionId\":\"null\", \"startServerTime\":\"null\", \"text\":\"null\"}");
    }

}
