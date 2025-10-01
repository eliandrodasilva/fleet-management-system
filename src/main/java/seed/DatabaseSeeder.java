package seed;

import service.VehicleService;
import model.Vehicle;

import java.util.List;

public class DatabaseSeeder {

    private VehicleService vehicleService = new VehicleService();

    public void seedAll() {
        seedVehicles();
    }

    private void seedVehicles() {
        List<Vehicle> vehicles = VehicleSeed.getVehicles();
        for (Vehicle v : vehicles) {
            try {
                vehicleService.createVehicle(v);
                System.out.println("Veículo cadastrado: " + v.getLicensePlate());
            } catch (IllegalArgumentException e) {
                System.out.println("Falha ao cadastrar veículo " + v.getLicensePlate() + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        DatabaseSeeder seeder = new DatabaseSeeder();
        seeder.seedAll();
        System.out.println("Seed concluído!");
    }
}
