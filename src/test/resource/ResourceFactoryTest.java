package resource;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by anton on 4/2/14.
 */
public class ResourceFactoryTest  {

    @Test
    public void testTwoInstance() {
        ResourceFactory.instanse();
        Assert.assertNotNull(ResourceFactory.instanse());
    }

    @Test
    public void testFirstInstance() {
        Assert.assertNotNull(ResourceFactory.instanse());
    }


}
