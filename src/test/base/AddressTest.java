package base;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 29.03.14
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
public class AddressTest {
    @Test
    public void testAddress() throws Exception {
        Assert.assertNotNull(new Address());
    }

    @Test
    public void testHashCode() throws Exception {
        int h1 = (new Address()).hashCode();
        int h2 = (new Address()).hashCode();
        Assert.assertEquals(++h1, h2);
    }
}
