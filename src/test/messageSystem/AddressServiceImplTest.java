package messageSystem;

import base.Abonent;
import base.Address;
import base.MessageSystem;
import junit.framework.Assert;
import messageSystem.AddressServiceImpl;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;
import sun.management.resources.agent_fr;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        MessageSystem messageSystem = mock(MessageSystem.class);
        AddressServiceImpl addressService1 = new AddressServiceImpl();
        Field field = addressService1.getClass().getDeclaredField("nameToQuantity");
        field.setAccessible(true);
        Map<String,Integer> nameToQuantity = mock( HashMap.class);
        when(nameToQuantity.containsKey(anyString())).thenReturn(true);
        when(nameToQuantity.get(anyString())).thenReturn(1);
//        nameToQuantity.put("opop", 12);
        field.set(addressService1, nameToQuantity);
        Abonent abonent1 = mock(Abonent.class);
        when(abonent1.getAddress()).thenReturn(new Address());
        addressService1.addService(abonent1, "1");
        verify(nameToQuantity, never()).put(anyString(), eq(1));
    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        MessageSystem messageSystem = mock(MessageSystem.class);
        AddressServiceImpl addressService1 = new AddressServiceImpl();
        Field field = addressService1.getClass().getDeclaredField("nameToQuantity");
        Map<String,Integer> nameToQuantity = mock( HashMap.class);
        field.setAccessible(true);
        when(nameToQuantity.containsKey(anyString())).thenReturn(true);
        when(nameToQuantity.get(anyString())).thenReturn(2);
        field.set(addressService1, nameToQuantity);
        Map<String,Integer> nameToLast = mock( HashMap.class);
        field = addressService1.getClass().getDeclaredField("nameToLast");
        field.setAccessible(true);
        field.set(addressService1, nameToLast);
        Abonent abonent1 = mock(Abonent.class);
        when(abonent1.getAddress()).thenReturn(new Address());
        addressService1.addService(abonent1, "1");
        verify(nameToLast,never()).put(anyString(),eq(1));
    }

}
