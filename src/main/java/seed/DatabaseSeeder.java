package seed;

import model.*;
import service.*;

import java.util.List;

public class DatabaseSeeder {

    private VehicleBrandService vehicleBrandService = new VehicleBrandService();
    private VehicleModelService vehicleModelService = new VehicleModelService();
    private VehicleService vehicleService = new VehicleService();
    private DriverService driverService = new DriverService();
    private ScheduledServiceService scheduledServiceService = new ScheduledServiceService();
    private LocationService locationService = new LocationService();


    public void seedAll() {
        seedVehicleBrands();
        seedVehicleModels();
        seedVehicles();
        seedDrivers();
        seedScheduledServices();
        seedLocations();
    }

    private void seedVehicleBrands() {
        List<VehicleBrand> brands = BrandSeed.getBrands();
        for (VehicleBrand b : brands) {
            try {
                vehicleBrandService.createVehicleBrand(b);
                System.out.println("Marca cadastrada: " + b.getName());
            } catch (IllegalArgumentException e) {
                System.out.println("Falha ao cadastrar marca " + b.getName() + ": " + e.getMessage());
            }
        }
    }

    private void seedVehicleModels() {
        List<VehicleBrand> brands = vehicleBrandService.findAll();
        List<VehicleModel> models = VehicleModelSeed.getModels(brands);
        for (VehicleModel m : models) {
            try {
                vehicleModelService.createVehicleModel(m);
                System.out.println("Modelo cadastrado: " + m.getName() + " (" + m.getBrand().getName() + ")");
            } catch (IllegalArgumentException e) {
                System.out.println("Falha ao cadastrar modelo " + m.getName() + ": " + e.getMessage());
            }
        }
    }

    private void seedVehicles() {
        List<Vehicle> vehicles = VehicleSeed.getVehicles(vehicleModelService.findAll());
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

    private void seedScheduledServices() {
        List<ScheduledService> services = ScheduledServiceSeed.getScheduledServices(vehicleService.getAllVehicles());

        for (ScheduledService s : services) {
            try {
                scheduledServiceService.createScheduledService(s);
                System.out.println("Serviço agendado para veículo: " + s.getVehicle().getLicensePlate());
            } catch (IllegalArgumentException e) {
                System.out.println("Falha ao cadastrar serviço para veículo " + s.getVehicle().getLicensePlate() + ": " + e.getMessage());
            }
        }
    }

    private void seedLocations() {
        List<Location> locations = LocationSeed.getLocations();
        for (Location l : locations) {
            try {
                locationService.createLocation(l);
                System.out.println("Local cadastrado: " + l.getName());
            } catch (IllegalArgumentException e) {
                System.out.println("Falha ao cadastrar local " + l.getName() + ": " + e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        DatabaseSeeder seeder = new DatabaseSeeder();
        seeder.seedAll();
        System.out.println("Seed concluído!");
    }
}
