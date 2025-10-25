package seed;

import model.VehicleBrand;
import model.VehicleModel;
import java.util.List;
import java.util.ArrayList;

public class VehicleModelSeed {

    public static List<VehicleModel> getModels(List<VehicleBrand> brands) {
        List<VehicleModel> models = new ArrayList<>();

        models.add(new VehicleModel("Paradiso 1200", brands.get(0)));
        models.add(new VehicleModel("Paradiso 1800 DD", brands.get(0)));
        models.add(new VehicleModel("O500", brands.get(0)));
        models.add(new VehicleModel("9700", brands.get(1)));
        models.add(new VehicleModel("9900", brands.get(1)));
        models.add(new VehicleModel("Lumina", brands.get(2)));
        models.add(new VehicleModel("Voyage", brands.get(3)));

        return models;
    }
}
