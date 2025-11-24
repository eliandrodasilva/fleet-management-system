package seed;

import model.Route;
import model.Driver;
import model.Vehicle;
import model.Location;
import service.DriverService;
import service.VehicleService;
import service.LocationService;

import java.util.ArrayList;
import java.util.List;

public class RouteSeed {

    private static final DriverService driverService = new DriverService();
    private static final VehicleService vehicleService = new VehicleService();
    private static final LocationService locationService = new LocationService();

    public static List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();

        List<Driver> drivers = driverService.findAll();
        List<Vehicle> vehicles = vehicleService.findAll();
        List<Location> locations = locationService.findAll();

        if (!drivers.isEmpty() && !vehicles.isEmpty() && locations.size() >= 2) {
            routes.add(new Route(
                    drivers.get(0),
                    vehicles.get(0),
                    locations.get(1),
                    locations.get(7)
            ));

            if (locations.size() >= 3 && drivers.size() > 1 && vehicles.size() > 1) {
                routes.add(new Route(
                        drivers.get(1),
                        vehicles.get(1),
                        locations.get(1),
                        locations.get(2)
                ));
            }
        }

        return routes;
    }
}
