package Utils;

import junit.framework.Assert;
import org.junit.Test;

import static utils.SHA2.getSHA2;

/**
 * Created by anton on 4/1/14.
 */
public class SHA2Test {
    @Test
    public void getSHA2StringTest() {
        String password =  getSHA2("opopop");
        Assert.assertEquals("Косяк с sha2", password, "f38f4b329cedc876f474e966766e0e9e0759f4bd286eeb46eb235fc82396ee8e" );
    }

    @Test
    public void getSHA2IntTest() {
        String password =  getSHA2(12);
        Assert.assertEquals("Косяк с sha2", password, "6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918" );
    }

    @Test
    public void getSHA2LongTest() {
        String password = getSHA2((long) 12);
        Assert.assertEquals("Косяк с sha2", password, "6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918" );
    }


}
