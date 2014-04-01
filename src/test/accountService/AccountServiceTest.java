package accountService;

import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 01.04.14
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceTest {
    AccountServiceImpl asi;
    int firstId, arbitraryId;

    @Before
    public void setUp() {
        asi = new AccountServiceImpl(new MessageSystemImpl());
    }

    @Test
    public void testGetFirstUserId() throws Exception {
        firstId = asi.getUserId("nickname1", "passwd123");
        Assert.assertEquals(firstId, 1);
    }

    @Test
    public void testGetArbitraryUserId() throws Exception {
        firstId = asi.getUserId("nickname1", "passwd123");
        arbitraryId = asi.getUserId("nickname2", "qwerty000");
        Assert.assertTrue(firstId != arbitraryId);
    }
}
