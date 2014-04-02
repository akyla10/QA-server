package Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.CookieDescriptor;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 02.04.14
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public class CookieDescriptorTest {
    Cookie[] coo = new Cookie[10];
    CookieDescriptor cd;


    @Before
    public void setUp() throws Exception {
        for(int i=0; i<coo.length; i++) {
            coo[i] = new Cookie("name_" + i, "value_"+i);
        }
        cd = new CookieDescriptor(coo);
    }

    @Test
    public void testGetNotExistingCookieByName() throws Exception {
        String res = cd.getCookieByName("some_cookie");
        Assert.assertNull(res);
    }

    @Test
    public void testGetExistingCookieByName() throws Exception {
        String res = cd.getCookieByName("name_3");
        Assert.assertEquals(res, "value_3");
    }
}
