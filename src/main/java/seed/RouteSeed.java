package seed;

import model.Location;
import model.Route;
import service.LocationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RouteSeed {

    private static final LocationService locationService = new LocationService();

    public static List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();

        List<Location> locations = locationService.findAll();

        routes.add(new Route(
                locations.get(1),
                locations.get(2),
                new BigDecimal("145")
        ));

        routes.add(new Route(
                locations.get(2),
                locations.get(3),
                new BigDecimal("68.8")
        ));

        routes.add(new Route(
                locations.get(2),
                locations.get(11),
                new BigDecimal("10")
        ));

        routes.add(new Route(
                locations.get(3),
                locations.get(4),
                new BigDecimal("61.2")
        ));

        routes.add(new Route(
                locations.get(4),
                locations.get(8),
                new BigDecimal("79.3")
        ));

        routes.add(new Route(
                locations.get(3),
                locations.get(5),
                new BigDecimal("48.4")
        ));

        routes.add(new Route(
                locations.get(5),
                locations.get(8),
                new BigDecimal("86.1")
        ));

        routes.add(new Route(
                locations.get(5),
                locations.get(6),
                new BigDecimal("16.3")
        ));

        routes.add(new Route(
                locations.get(6),
                locations.get(7),
                new BigDecimal("16.4")
        ));

        routes.add(new Route(
                locations.get(7),
                locations.get(8),
                new BigDecimal("75.2")
        ));

        routes.add(new Route(
                locations.get(8),
                locations.get(9),
                new BigDecimal("60.9")
        ));

        routes.add(new Route(
                locations.get(9),
                locations.get(10),
                new BigDecimal("38.1")
        ));

        routes.add(new Route(
                locations.get(10),
                locations.get(12),
                new BigDecimal("10")
        ));

        return routes;
    }
}
