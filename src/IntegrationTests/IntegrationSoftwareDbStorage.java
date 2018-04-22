package IntegrationTests;

import java.sql.Connection;
import java.sql.SQLException;

import no.ntnu.fp.storage.FactoryDbStorage;
import no.ntnu.fp.storage.SoftwareDbStorage;
import org.junit.Assert;
import org.junit.Test;

public class IntegrationSoftwareDbStorage {

    FactoryDbStorage fds = new FactoryDbStorage();
    SoftwareDbStorage sds = new SoftwareDbStorage();

    @Test
    public void testConnectToDb() throws SQLException {
        // SoftwareDbStorage calls connectToFactoryDb, hence this method is tested for integration.
        Connection c = fds.connectToFactoryDb();
        Assert.assertTrue(c.isValid(5));
    }

}
