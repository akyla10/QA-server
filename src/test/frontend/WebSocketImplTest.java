package frontend;

import base.Abonent;
import base.MessageSystem;
import com.sun.corba.se.impl.corba.AnyImpl;
import junit.framework.TestCase;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

/**
 * Created by anton on 4/9/14.
 */
public class WebSocketImplTest extends TestCase {

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
        verify(spy, never()).checkStroke(anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyString());
        verify(spy, never()).addNewWS(anyString());
     }
    
    @Test
    public  void testConstructor() {
    }

}
