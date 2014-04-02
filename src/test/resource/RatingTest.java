package resource;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import resource.Rating;

import java.io.PrintStream;

/**
 * Created by anton on 3/26/14.
 */
public class RatingTest {

    @Before
    public void init() {
        Rating.maxDiff = 5;
        Rating.minDiff =3;
        Rating.avgDiff =8;
    }

    @Test
    public void simpleTestDTNotZero() {
        Rating.decreaseThreshold = 4;

        Assert.assertEquals(Rating.getDiff(4,10 ), 5);
    }

    @Test
    public void simpleTestDTIsZero() {
        Rating.decreaseThreshold = 0;

        Assert.assertEquals(Rating.getDiff(4,10 ), Rating.getAvgDiff());
    }
}
