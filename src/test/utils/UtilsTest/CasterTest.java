package utils.UtilsTest;


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
        System.out.println("test");
        Caster c = new Caster();
        Map<String, String> m = new HashMap<String, String>();
        m.put("a","b");
        m.put("ee","rr");
        m.put("aa","ss");
        String s[] = Caster.castKeysToStrings(m);
        System.out.println(Arrays.toString(s));
    }

}
