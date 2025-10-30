package seed;

import model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationSeed {

    public static List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();

        locations.add(new Location("Localidade de Teste"));
        locations.add(new Location("Terminal Rodoviário Guaíra (Paraguai)"));
        locations.add(new Location("Umuarama"));
        locations.add(new Location("Cidade Gaúcha"));
        locations.add(new Location("Cianorte"));
        locations.add(new Location("Paraíso do Norte"));
        locations.add(new Location("Tamboara"));
        locations.add(new Location("Paranavaí"));
        locations.add(new Location("Maringá"));
        locations.add(new Location("Arapongas"));
        locations.add(new Location("Londrina"));
        locations.add(new Location("Oficina (Umuarama)"));
        locations.add(new Location("Oficina (Londrina)"));

        return locations;
    }
}