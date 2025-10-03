package seed;

import model.Driver;
import service.DriverService;
import service.VehicleService;
import model.Vehicle;

import java.util.List;

public class DatabaseSeeder {

    private VehicleService vehicleService = new VehicleService();
    private DriverService driverService = new DriverService();

    public void seedAll() {
        seedVehicles();
        seedDrivers();
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

    private void seedDrivers() {
        List<Driver> drivers = DriverSeed.getDrivers();
        for (Driver d : drivers) {
            try {
                driverService.createDriver(d);
                System.out.println("Motorista cadastrado: " + d.getName());
            } catch (IllegalArgumentException e) {
                System.out.println("Falha ao cadastrar motorista " + d.getName() + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        DatabaseSeeder seeder = new DatabaseSeeder();
        seeder.seedAll();
        System.out.println("Seed concluído!");
    }
}
