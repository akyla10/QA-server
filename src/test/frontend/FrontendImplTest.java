package frontend;

import base.MessageSystem;
import dbService.UserDataSet;
import junit.framework.Assert;
import junit.framework.TestCase;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Request;
import org.junit.Before;
import org.junit.Test;
import utils.SysInfo;
import utils.TemplateHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicInteger;

import static frontend.FrontendImpl.*;
import static frontend.UserDataImpl.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static utils.SHA2.getSHA2;
import static utils.TemplateHelper.renderTemplate;

/**
 * Created by anton on 4/5/14.
 */
public class FrontendImplTest  {

    MessageSystem messageSystem;
    FrontendImpl frontend;
    Request request;
    HttpServletRequest httpRequest;
    HttpServletResponse httpResponse;
    UserDataImpl userData;
    AtomicInteger session;
    //enum status {nothing,haveCookie,haveCookieAndPost,waiting,ready}


    @Before
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        frontend = new FrontendImpl(messageSystem);
        request = mock(Request.class);
        httpRequest = mock(HttpServletRequest.class);
        httpResponse = mock(HttpServletResponse.class);
        userData = new UserDataImpl(messageSystem);
        session  = new AtomicInteger();


    }

    public Cookie[] setSessionCookies(String session, String c1, String c2) {
        if(session!=null)  {
            Cookie[] c = new Cookie[2];
            c[0] = new Cookie("sessionId", session);
            c[1] = new Cookie("startServerTime", getStartServerTime());
            return c;
        } else if(c1!=null&&c2!=null) {
            Cookie[] c = new Cookie[2];
            c[0] = new Cookie("sessionId", c1);
            c[1] = new Cookie("startServerTime", c2);
            return c;
        } else if(c1!=null) {
            Cookie[] c = new Cookie[1];
            c[0] = new Cookie("sessionId", c1);
            return c;
        } else if(c2!=null) {
            Cookie[] c = new Cookie[1];
            c[0] = new Cookie("startServerTime", c2);
            return c;
        } else
            return new Cookie[0];
    }


    @Test
    public void controllerIndexTest() {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertEquals(
                "index.html",
                frontend.onHaveCookieStatus("/", new UserDataSet(), httpServletResponse)
        );
    }

    @Test
    public void controllerRegTest() {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertEquals(
                "reg.html",
                frontend.onHaveCookieStatus("/reg", new UserDataSet(), httpServletResponse)
        );
    }

    @Test
    public void controllerTest() {
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertNull(
                frontend.onHaveCookieStatus("opopop.html", new UserDataSet(), httpServletResponse)
        );
    }

    @Test
    public void testAddressNotNull() throws Exception {
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null, null, null));
        frontend.handle("/profile", request, httpRequest, httpResponse);
        Assert.assertNotNull(frontend.getAddress());
    }

    @Test
    public void testTargets() throws Exception {
        new SysInfo();

        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null, null, null));
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
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null, null, null));
        frontend.handle("/unknown", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
    }

    @Test
    public void testHandleStatic() throws Exception {
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null, null, null));
        frontend.handle("/js/unknownscript.js", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");

        frontend.handle("/js/", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
        frontend.handle("/img/", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
        frontend.handle("/css/", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
        frontend.handle("/s", request, httpRequest, httpResponse);
        Assert.assertEquals(TemplateHelper.currentPage, "404.html");
    }


    @Test
    public void testHandleNewUserCondition() throws Exception {
        String newSession = getSHA2(String.valueOf(session.incrementAndGet()));
        when(this.httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null, null, null));

        frontend.handle("/", request, httpRequest, httpResponse);
        Assert.assertTrue(containsSessionId(newSession));


        when(httpRequest.getCookies()).thenReturn(setSessionCookies(null, newSession, null));
        frontend.handle("/", request, httpRequest, httpResponse);
        newSession = getSHA2(String.valueOf(session.incrementAndGet()));
        Assert.assertTrue(containsSessionId(newSession));
    }

    @Test
    public void testGetStatus() throws Exception {
        String newSession = getSHA2(String.valueOf(session.incrementAndGet()));
        when(this.httpRequest.getMethod()).thenReturn("POST");
        status stat = frontend.getStatus(httpRequest, "/", status.haveCookie, newSession);
        Assert.assertTrue(stat.equals(status.haveCookieAndPost));

        System.out.println(newSession);
        putSessionIdAndUserSession(newSession, new UserDataSet());
        System.out.println(getUserSessionBySessionId(newSession).getId());
    }

    @Test
    public void  onReadyStatusTest(){
        MessageSystem ms = mock(MessageSystem.class);
        FrontendImpl frontend1 = new FrontendImpl(ms);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertEquals(
                "index.html",
                frontend1.onHaveCookieStatus("/", new UserDataSet(), httpServletResponse)
        );
    }

    @Test
    public void  onReadyStatusGameTest(){
        MessageSystem ms = mock(MessageSystem.class);
        FrontendImpl frontend1 = new FrontendImpl(ms);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertEquals(
                "game.html",
                frontend1.onReadyStatus("/game", "e", new UserDataSet(), httpServletResponse)
        );
    }

    @Test
    public void onReadyStatusLogoutTest(){
        MessageSystem ms = mock(MessageSystem.class);
        FrontendImpl frontend1 = new FrontendImpl(ms);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertEquals(
                "/",
                frontend1.onReadyStatus("/logout", "e", new UserDataSet(), httpServletResponse)
        );
    }

    @Test
    public void onReadyStatusProfileTest(){
        MessageSystem ms = mock(MessageSystem.class);
        FrontendImpl frontend1 = new FrontendImpl(ms);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertEquals(
                "profile.html",
                frontend1.onReadyStatus("/profile","2", new UserDataSet(), httpServletResponse)
        );
    }
    @Test
    public void onReadyStatusElseTest(){
        MessageSystem ms = mock(MessageSystem.class);
        FrontendImpl frontend1 = new FrontendImpl(ms);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        Assert.assertNull(
                frontend1.onReadyStatus("opopop",",", new UserDataSet(), httpServletResponse)
        );
    }
}
