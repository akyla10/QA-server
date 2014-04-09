package frontend;

import base.Abonent;
import base.MessageSystem;
import base.UserData;
import base.WebSocket;
import com.sun.corba.se.impl.corba.AnyImpl;
import dbService.UserDataSet;
import junit.framework.Assert;
import junit.framework.TestCase;
import netscape.javascript.JSObject;
import org.apache.tools.ant.types.resources.First;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by anton on 4/9/14.
 */
public class WebSocketImplTest  {

    @Test
    public void test() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("-1");
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("-1");
                when(jsObject.get("from_y")).thenReturn("-1");
                when(jsObject.get("to_x")).thenReturn("-1");
                when(jsObject.get("to_y")).thenReturn("-1");
                when(jsObject.get("status_y")).thenReturn("-1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void test1() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("-1");
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("-1");
                when(jsObject.get("to_x")).thenReturn("-1");
                when(jsObject.get("to_y")).thenReturn("-1");
                when(jsObject.get("status_y")).thenReturn("-1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void test2() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("-1");
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("-1");
                when(jsObject.get("to_y")).thenReturn("-1");
                when(jsObject.get("status_y")).thenReturn("-1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void test3() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("-1");
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("1");
                when(jsObject.get("to_y")).thenReturn("-1");
                when(jsObject.get("status_y")).thenReturn("-1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void test4() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("-1");
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("1");
                when(jsObject.get("to_y")).thenReturn("1");
                when(jsObject.get("status_y")).thenReturn(null);
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void test5() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("-1");
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("1");
                when(jsObject.get("to_y")).thenReturn("1");
                when(jsObject.get("status_y")).thenReturn("1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void onWebSocketTextTrue() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn("1");
                when(jsObject.get("startServerTime")).thenReturn(UserDataImpl.getStartServerTime());
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("1");
                when(jsObject.get("to_y")).thenReturn("1");
                when(jsObject.get("status_y")).thenReturn("1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, atLeast(1)).checkStroke(anyString(),anyInt(),anyInt(),anyInt(),anyInt(),anyString());
    }

    @Test
    public void onWebSocketTextSessionIdNull() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn(null);
                when(jsObject.get("startServerTime")).thenReturn(UserDataImpl.getStartServerTime());
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("1");
                when(jsObject.get("to_y")).thenReturn("1");
                when(jsObject.get("status_y")).thenReturn("1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never() ).checkStroke(anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyString());
    }

    @Test
    public void test6() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);

        when(spy.parse(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                JSONObject jsObject = mock(JSONObject.class);
                when(jsObject.get("sessionId")).thenReturn(null);
                when(jsObject.get("startServerTime")).thenReturn("-1");
                when(jsObject.get("from_x")).thenReturn("1");
                when(jsObject.get("from_y")).thenReturn("1");
                when(jsObject.get("to_x")).thenReturn("1");
                when(jsObject.get("to_y")).thenReturn("1");
                when(jsObject.get("status_y")).thenReturn("1");
                return jsObject;
            }
        });
        when(spy.isNotConnected()).thenReturn(Boolean.FALSE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyString());
        verify(spy, never()).addNewWS(anyString());
    }

    @Test
    public void test7() throws ParseException {
        WebSocketImpl webSocket = new WebSocketImpl(true);
        MessageSystem messageSystem = mock(MessageSystem.class);
        Abonent abonent = mock(Abonent.class);

        WebSocketImpl spy = spy(webSocket);
        spy.setMS(messageSystem);
        when(spy.isNotConnected()).thenReturn(Boolean.TRUE);
        spy.onWebSocketText("opop");
        verify(spy, never()).checkStroke(anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyString());
        verify(spy, never()).addNewWS(anyString());
     }

    @Test
    public void updateUsersColorTest() throws NoSuchFieldException, IOException {
        WebSocketImpl webSocket =  new WebSocketImpl(false);
        UserDataImpl userData1 = mock(UserDataImpl.class);
        UserDataImpl userData2 = mock(UserDataImpl.class);
        WebSocketImpl webSocket1 = mock(WebSocketImpl.class);
        WebSocketImpl webSocket2 = mock(WebSocketImpl.class);

        UserDataSet userDataSet1 = mock(UserDataSet.class);
        UserDataSet userDataSet2 = mock(UserDataSet.class);

        RemoteEndpoint remoteEndpoint1 = mock(RemoteEndpoint.class);

        UserDataImpl.putLogInUser("id1", userDataSet1);
        UserDataImpl.putLogInUser("id2", userDataSet2);
        WebSocketImpl spy = spy(webSocket);
        when(spy.getWSBySessionId(anyString())).thenReturn( remoteEndpoint1);
        Map<String, String> usersToColors = new HashMap<String, String>();
        usersToColors.put("id1", "white");
        usersToColors.put("id2", "black");
        spy.updateUsersColor(usersToColors);
        verify(spy,atLeastOnce()).getWSBySessionId(anyString());
    }

    @Test
    public void parseTest() {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        JSONObject jsonObject = webSocket.parse("{\"id\":\"2\"}");
        Assert.assertNotNull(jsonObject);
    }

    @Test
    public void parseErrorTest() {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        JSONObject jsonObject = webSocket.parse("{\"jfsdjhsd\"fk\"}");
        Assert.assertNull(jsonObject);
    }

     @Test
    public void addNewWSFalseTest() {
        WebSocketImpl webSocket = new WebSocketImpl();
            Assert.assertFalse(
                    webSocket.addNewWS("s")
            );
    }

}
