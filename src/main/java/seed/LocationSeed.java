package seed;

import model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationSeed {

    public static List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();

        locations.add(new Location("Garagem Central"));
        locations.add(new Location("Cidade Gaúcha (Garagem Central)"));
        locations.add(new Location("Tamboara (Garagem Secundária)"));
        locations.add(new Location("Oficina Norte"));
        locations.add(new Location("Terminal Rodoviário Guaíra (Paraguai)"));

        return locations;
    }
}