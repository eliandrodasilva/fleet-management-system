package seed;

import model.Vehicle;
import model.VehicleModel;
import model.enums.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

public class VehicleSeed {
    public static List<Vehicle> getVehicles(List<VehicleModel> models) {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("ABC1234", models.get(0), 2015, 120000, VehicleStatus.AVAILABLE));
        vehicles.add(new Vehicle("JKL2468", models.get(1), 2021, 60000, VehicleStatus.AVAILABLE));
        vehicles.add(new Vehicle("XYZ5678", models.get(2), 2018, 80000, VehicleStatus.IN_MAINTENANCE));
        vehicles.add(new Vehicle("DEF9876", models.get(3), 2020, 50000, VehicleStatus.IN_USE));
        vehicles.add(new Vehicle("MNO1357", models.get(4), 2022, 40000, VehicleStatus.IN_USE));
        vehicles.add(new Vehicle("GHI5432", models.get(5), 2019, 75000, VehicleStatus.INACTIVE));
        vehicles.add(new Vehicle("HOT6969", models.get(6), 1987, 120000, VehicleStatus.AVAILABLE));
        return vehicles;
    }
}
