package IntegrationTests;

import no.ntnu.fp.gui.*;
import no.ntnu.fp.model.FactoryProject;
import no.ntnu.fp.model.Project;
import no.ntnu.fp.model.Vehicle;
import no.ntnu.fp.storage.VehicleDbStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class IntegrationNewVehiclePanel {

    FactoryProjectPanel fpp;
    FactoryProject fp;
    NewVehiclePanel vp;
    VehicleDbStorage vds;
    Vehicle v;

    int vid;

    @Before
    public void setup(){
        fpp = new FactoryProjectPanel();
        fp = new FactoryProject();
        fpp.setModel(fp);
        vp = new NewVehiclePanel(fpp);
        vds = new VehicleDbStorage();
        vid = vds.openVehicles().size()+1;
        v = new Vehicle(String.valueOf(vid), "nothing", new ArrayList(), "100");
        vp.setModel(v);

    }

    @Test
    public void testAddVehicle(){
        // NewVehiclePanel calls addVehicle, hence this method is tested for integration.
        vds.addVehicle(v, vp);

        Vehicle retrieved = vds.getVehicle(vid);
        Assert.assertEquals(retrieved.getVehicleID(), v.getVehicleID());
        Assert.assertEquals(retrieved.getSeries(), v.getSeries());
    }

    @Test
    public void testFactoryProjectVehiclesList(){
        // CollectVehicles of NewVehiclePanel calls FactoryProject's getVehicleCount and getLatestVehicle,
        // hence these methods are tested for integration.
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(v);
        fp.addVehicle(v);

        Assert.assertEquals(vehicles.size(), fp.getVehicleCount());
        Assert.assertEquals(vehicles.get(0), fp.getLatestVehicle());

    }

}
