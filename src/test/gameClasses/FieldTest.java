package gameClasses;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 4/1/14.
 */
public class FieldTest {
    Field field;

    @Before
    public void init() {
        field = new Field();
    }

    @Test
    public void testNothing() {
        field.setType(Field.checker.nothing);
        Assert.assertTrue(field.getType().equals(Field.checker.nothing));
    }

    @Test
    public void testWhite() {
        field.setType(Field.checker.white);
        Assert.assertTrue(field.getType().equals(Field.checker.white));
    }

    @Test
    public void testBlack() {
        field.setType(Field.checker.black);
        Assert.assertTrue(field.getType().equals(Field.checker.black));
    }

    public void testNewFieldIsEmpty() {
        Field f = new Field(Field.checker.white);
        Assert.assertTrue(f.isEmpty());
    }

}
