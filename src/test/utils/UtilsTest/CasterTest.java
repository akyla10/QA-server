package utils.UtilsTest;


import junit.framework.Assert;
import org.junit.Test;
import utils.Caster;

import java.util.*;

import org.junit.Test;
import utils.Caster;

import java.lang.Exception;
import java.lang.System;
import java.util.HashMap;
import java.util.Map;


public class CasterTest{
    @Test
    public void simpleTest() {
        Caster c = new Caster();

        Map<String, String> m = new HashMap<String, String>();
        m.put("a", "b");
        m.put("22", "33");
        String s[] = Caster.castKeysToStrings(m);
        Assert.assertTrue("Неправильное количество" ,s.length == 2);
    }

}
