package seed;

import model.Vehicle;
import model.enums.VehicleModel;
import model.enums.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

public class VehicleSeed {

    public static List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("ABC1234", VehicleModel.PARADISO_1200, 2015, 120000, VehicleStatus.AVAILABLE));
        vehicles.add(new Vehicle("JKL2468", VehicleModel.PARADISO_1800_DD, 2021, 60000, VehicleStatus.AVAILABLE));
        vehicles.add(new Vehicle("XYZ5678", VehicleModel.MERCEDES_O500, 2018, 80000, VehicleStatus.IN_MAINTENANCE));
        vehicles.add(new Vehicle("DEF9876", VehicleModel.VOLVO_9700, 2020, 50000, VehicleStatus.IN_USE));
        vehicles.add(new Vehicle("MNO1357", VehicleModel.VOLVO_9900, 2022, 40000, VehicleStatus.IN_USE));
        vehicles.add(new Vehicle("GHI5432", VehicleModel.LUMINA_COMIL, 2019, 75000, VehicleStatus.INACTIVE));

        return vehicles;
    }
}
