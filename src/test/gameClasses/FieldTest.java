package gameClasses;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 4/1/14.
 */
public class FieldTest {
    Field field;

    @Test
    public void testNewFieldIsNotEmpty() {
        Field f = new Field(Field.checker.white);
        Assert.assertFalse(f.isEmpty());
    }

    @Test
    public void testNewFieldIsEmpty() {
        Field f = new Field(Field.checker.nothing);
        Assert.assertTrue(f.isEmpty());
    }

}
