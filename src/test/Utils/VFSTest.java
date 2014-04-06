package Utils;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import utils.VFS;

import java.io.File;
import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 04.04.14
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class VFSTest {
    String projPath =  System.getProperty("user.dir");
    String absolutePath =  System.getProperty("user.home") + "/IdeaProjects/QA-server/statistic/time";
    String relativePath =  "statistic/time";
    String emptyFilePath = "/statistic/empty";

    @Test
    public void testGetAbsolutePath() throws Exception {
        String ap1 = VFS.getAbsolutePath(absolutePath);
        String ap2 = VFS.getAbsolutePath(relativePath);
        Assert.assertEquals(ap1, ap2);
    }

    @Test
    public void testGetRelativePath() throws Exception {
        String rp1 = VFS.getRelativePath(absolutePath);
        String rp2 = VFS.getRelativePath(relativePath);
        Assert.assertEquals(rp1, rp2);
    }

    @Test
    public void testWriteToEndOfFile() throws Exception {
        VFS.writeToEndOfFile(emptyFilePath, "end of file");
        Assert.assertTrue(VFS.readFile(emptyFilePath).endsWith("end of file"));
    }

    @Test
    public void testReadAndWrite() throws Exception {
        VFS.writeToFile(emptyFilePath, "test");
        Assert.assertEquals(VFS.readFile(emptyFilePath), "test");
    }

    @Test
    public void normalizePathEmptyTest() {
        Assert.assertEquals(
                "/",
                VFS.normalizePath("")
        );
    }

    @Test
    public void normalizePathOneSymbolTest() {
        Assert.assertEquals(
                "/o",
                VFS.normalizePath("o")
        );
    }

    @After
    public void tearDown() throws Exception {
        File f = new File(VFS.getAbsolutePath(emptyFilePath));
        FileWriter fw = new FileWriter(f);
        fw.write(new String());
    }


}
