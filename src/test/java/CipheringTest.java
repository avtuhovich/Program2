import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class CipheringTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void crypt() throws IOException {
        File fl = folder.newFile("input");
        FileUtils.writeStringToFile(fl, "Tests");
        Ciphering ciphering = new Ciphering("key", fl.getAbsolutePath());
        Assert.assertEquals(" \u000E\u0003\n2\u0018\u001E\u0007<\n\b\u00189\n\u001D\u001F", ciphering.coding());
    }

    @Test
    public void decrypt() throws IOException {
        File fl = folder.newFile("input");
        File flo = folder.newFile("output");
        FileUtils.writeStringToFile(fl, " \u000E\u0003\n2\u0018\u001E\u0007<\n\b\u00189\n\u001D\u001F");
        Ciphering program = new Ciphering("key", fl.getAbsolutePath());
        Assert.assertEquals("Tests", program.coding());
    }
}