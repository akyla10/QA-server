package Utils;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.SysInfo;
import utils.TimeHelper;
import utils.VFS;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 05.04.14
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
public class SysInfoTest {
    public final String testFile = "/statistic/ccu";
    SysInfo si;

    @Before
    public void setUp() throws Exception {

        si = new SysInfo();

    }

    @Test
    public void testSysInfo() throws Exception {
        File f = new File(VFS.getAbsolutePath(testFile));
        f.delete();

        Assert.assertTrue(!f.exists());

        (new Thread(si)).start();
        TimeHelper.sleep(1000);
        String testFileContent = VFS.readFile(f.getPath());
        System.out.println(testFileContent);

        Assert.assertTrue(f.exists());
        TimeHelper.sleep(10000);

        Assert.assertFalse(testFileContent.equals(VFS.readFile(f.getPath())));

    }

    @Test
    public void testGetStat() throws Exception {
        String s = SysInfo.getStat("MemoryUsage");
        Assert.assertNotNull(s);
        Assert.assertTrue(s.startsWith("["));
        Assert.assertTrue(s.endsWith("]"));

    }
}
