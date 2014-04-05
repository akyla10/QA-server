package Utils;

import junit.framework.Assert;
import org.junit.Test;
import utils.TimeHelper;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 05.04.14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class TimeHelperTest {
    @Test
    public void testGetGMT() throws Exception {
        String time = TimeHelper.getGMT();
        Assert.assertNotNull(time);

    }

    @Test
    public void testGetTime() throws Exception {
        String time = TimeHelper.getTime();
        Assert.assertNotNull(time);
    }
}
