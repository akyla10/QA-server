package Utils;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 02.04.14
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionHelperTest {
    Object obj;
    String properFieldName, lameFieldName, intValue, strValue, existingClass, notExistingClass, properStrField;

    @Before
    public void setUp() throws Exception {
        existingClass =  "resource.GameSettings";
        notExistingClass = "totally_weird_classname";
        properFieldName = "strokeTime";
        properStrField = "strField";
        lameFieldName = "fieldSize";
        intValue = "42";
        strValue = "string";
    }

    @Test
    public void testCreateInstanceOfNotExistingClass() throws Exception {
        obj = ReflectionHelper.createInstance(notExistingClass);
        Assert.assertNull(obj);
    }

    @Test
    public void testCreateInstanceOfExistingClass() throws Exception {
        obj = ReflectionHelper.createInstance(existingClass);
        Assert.assertNotNull(obj);
    }

    @Test
    public void testSetIntFieldValue() throws Exception {
        obj = ReflectionHelper.createInstance(existingClass);
        ReflectionHelper.setFieldValue(obj, properFieldName, intValue);
        //Assert.assertNotNull(obj);
    }

    @Test
    public void testSetStrFieldValue() throws Exception {
        obj = ReflectionHelper.createInstance(existingClass);
        ReflectionHelper.setFieldValue(obj, properStrField, strValue);
        //Assert.assertNotNull(obj);
    }

    @Test
    public void testLameFieldValue() throws Exception {
        obj = ReflectionHelper.createInstance(existingClass);
        ReflectionHelper.setFieldValue(obj, "weirdnesshere", strValue);
        //Assert.assertNotNull(obj);
    }
}
