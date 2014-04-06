package frontend;

import base.MessageSystem;
import junit.framework.Assert;
import junit.framework.TestCase;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Request;
import org.junit.Test;
import utils.SysInfo;
import utils.TemplateHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static frontend.UserDataImpl.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static utils.TemplateHelper.renderTemplate;

/**
 * Created by anton on 4/5/14.
 */
public class FrontendImplTest extends TestCase {

    MessageSystem messageSystem;
    FrontendImpl frontend;
    Request request;
    HttpServletRequest httpRequest;
    HttpServletResponse httpResponse;
    UserDataImpl userData;


    @Override
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        frontend = new FrontendImpl(messageSystem);
        request = mock(Request.class);
        httpRequest = mock(HttpServletRequest.class);
        httpResponse = mock(HttpServletResponse.class);
        userData = new UserDataImpl(messageSystem);


    }

    public Cookie[] setSessionCookies(String session) {
        Cookie[] c = new Cookie[2];
        if(session!=null)  {
            c[0] = new Cookie("sessionId", session);
            c[1] = new Cookie("startServerTime", getStartServerTime());
            return c;
        } else
            return new Cookie[0];
    }

    @Test
    public void testAddressNotNull() throws Exception {
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null));
        frontend.handle("/profile", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
    }

    @Test
    public void testTargets() throws Exception {
        SysInfo sysInfo = new SysInfo();

        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null));
        frontend.handle("/", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
        frontend.handle("/wait", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
        frontend.handle("/game", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
        frontend.handle("/admin", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
        frontend.handle("/rules", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
        frontend.handle("/logout", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
        frontend.handle("/reg", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
    }

    @Test
    public void testHandle404() throws Exception {
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null));
        frontend.handle("/unknown", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
    }

    @Test
    public void testHandleStatic() throws Exception {
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null));
        frontend.handle("/js/unknownscript.js", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
    }


    @Test
    public void testHandle() throws Exception {
//        frontend.handle("/profile", request, httpRequest, httpResponse);
//        Map<String,String> m = new HashMap<String, String>();




    }
}
