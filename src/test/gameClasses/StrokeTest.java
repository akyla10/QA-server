package gameClasses;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 4/2/14.
 */
public class StrokeTest {
    private Stroke stroke;

    @Test
    public void inverseBlack() {
        stroke = new Stroke(10,10,10,10,"", "b" );
        Stroke strokeInverced = stroke.getInverse();
        Assert.assertEquals(strokeInverced.getColor(), "w");
    }

    @Test
    public void inverseWhite() {
        stroke = new Stroke(10,10,10,10,"", "w" );
        Stroke strokeInverced = stroke.getInverse();
        Assert.assertEquals(strokeInverced.getColor(), "b");
    }

    @Test
    public void inverseClear() {
        stroke = new Stroke(10,10,10,10,"", "w" );
        stroke.clear();
        Assert.assertEquals(stroke.getColor(), "");
    }

    @Test
    public void isEmptyTrue() {
        stroke = new Stroke(1,10,1,10,"");
        Assert.assertFalse(stroke.isEmpty());
        stroke.fullSet(-1,-1,-1,1);
        Assert.assertFalse(stroke.isEmpty());
        stroke.fullSet(-1,-1,1,1);
        Assert.assertFalse(stroke.isEmpty());
        stroke.fullSet(-1,1,1,1);
        Assert.assertFalse(stroke.isEmpty());
        stroke.fullSet(1,1,1,1);
        Assert.assertFalse(stroke.isEmpty());
    }


    @Test
    public void isEmptyFalse() {
        stroke = new Stroke(-1,-1,-1,-1,"", "w" );
        Assert.assertTrue(stroke.isEmpty());
    }

    public void CopyConstructorTest() {
        stroke = new Stroke(1,10,1,10,"", "w");
        Stroke stroke1 = new Stroke(stroke);
        Assert.assertEquals(stroke1.getColor(), "w");

    }
}
