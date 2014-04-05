package Utils;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.Attributes;
import utils.SAXHandler;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 05.04.14
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class SAXHandlerTest {
    SAXHandler sh = new SAXHandler();
    Attributes atr = Mockito.mock(Attributes.class);
    String className = "SAXHandler";
    char[] array = {'a', 'b', 'c'};

    @Before
    public void setUp() throws Exception {
        Mockito.when(atr.getValue(0)).thenReturn(className);
    }

    @Test
    public void testStartElement() throws Exception {

        sh.characters(array, 1, 1);
        sh.startElement("", "", "", atr);
        Assert.assertNull(sh.object);

        sh.startElement("", "", "class", atr);
        sh.endElement("", "", "");
        Assert.assertNull(sh.object);
    }
}
