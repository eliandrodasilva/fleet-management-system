package seed;

import model.VehicleBrand;

import java.util.Arrays;
import java.util.List;

public class BrandSeed {

    public static List<VehicleBrand> getBrands() {
        return Arrays.asList(
                new VehicleBrand("Mercedes-Benz"),
                new VehicleBrand("Volvo"),
                new VehicleBrand("Comil"),
                new VehicleBrand("Volkswagen")
        );
    }
}
