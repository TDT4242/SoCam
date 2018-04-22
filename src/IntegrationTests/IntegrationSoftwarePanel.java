package IntegrationTests;

import no.ntnu.fp.gui.FactoryProjectPanel;
import no.ntnu.fp.gui.SoftwarePanel;
import no.ntnu.fp.model.FactoryProject;
import no.ntnu.fp.model.Software;
import no.ntnu.fp.storage.SoftwareDbStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntegrationSoftwarePanel {

    FactoryProjectPanel fpp;
    SoftwarePanel sp;
    SoftwareDbStorage sds;
    Software sw;
    FactoryProject fp;

    int major = 123;
    int minor = 0;
    String url = "www.google.com";

    @Before
    public void setup(){
        fpp = new FactoryProjectPanel();
        fp = new FactoryProject();
        fpp.setModel(fp);
        sp = new SoftwarePanel(fpp);
        sw = new Software(major, minor, url);
        sp.setModel(sw);
        sds = new SoftwareDbStorage();
    }

    @Test
    public void testAddSoftware(){
        // SoftwarePanel calls addSoftware of SoftwareDbStorage, hence this method is tested for integration.
        sds.addSoftware(sw, sp);

        Assert.assertTrue(sds.swInSwArchive(sw.getSwVersion()));
        Assert.assertEquals(sw.getMinorVersion(), sds.getBiggestSubId(sw.getSwVersion()));
    }

    @Test
    public void testGetLatestSoftware(){
        // SoftwarePanel calls getLatestSoftware of FactoryProject, hence this method is tested for integration.
        fp.addSoftware(sw);

        Assert.assertEquals(sw, fp.getLatestSoftware());
    }


}
