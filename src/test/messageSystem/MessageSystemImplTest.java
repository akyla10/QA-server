package messageSystem;

import base.Abonent;
import base.Address;
import base.Msg;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 08.04.14
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
public class MessageSystemImplTest {
    AddressServiceImpl addressService;
    Abonent abonent;
    Address address;
    MessageSystemImpl ms;
    Msg msg;

    @Before
    public void setUp() throws Exception {
        ms = new MessageSystemImpl();

        abonent = mock(Abonent.class);
        address = mock(Address.class);
        msg = mock(Msg.class);

        when(abonent.getAddress()).thenReturn(address);
    }

    @Test
    public void testAddService() throws Exception {
        ms.addService(abonent, "name");
    }

    @Test
    public void testGetAddressByName() throws Exception {
        System.out.println(ms.getAddressByName("name"));
    }
}
