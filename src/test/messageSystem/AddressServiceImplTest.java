package messageSystem;

import base.Abonent;
import base.Address;
import junit.framework.Assert;
import messageSystem.AddressServiceImpl;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 08.04.14
 * Time: 0:02
 * To change this template use File | Settings | File Templates.
 */
public class AddressServiceImplTest {
    AddressServiceImpl addressService;
    Abonent abonent;
    Address address;
    MessageSystemImpl ms;

    @Before
    public void setUp() throws Exception {
        addressService = new AddressServiceImpl();
        ms = new MessageSystemImpl();
        abonent = mock(Abonent.class);
        address = mock(Address.class);

        when(abonent.getAddress()).thenReturn(address);
    }

    @Test
    public void testAddService() throws Exception {
        addressService.addService(abonent, "name");
        Assert.assertEquals(addressService.getAddressByName("name"), address);

        Assert.assertEquals(null, addressService.getAddressByName("nonExistingName"));
    }
}
