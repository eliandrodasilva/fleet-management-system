package service;

import dao.RouteDAO;
import model.Route;

import java.util.List;

public class RouteService {

    private final RouteDAO routeDAO = new RouteDAO();
    private final DriverService driverService = new DriverService();
    private final VehicleService vehicleService = new VehicleService();
    private final LocationService locationService = new LocationService();

    public void createRoute(Route route) {
        validateRoute(route);

        if (routeDAO.existsByDriverAndVehicle(route.getDriver().getId(), route.getVehicle().getId())) {
            throw new IllegalArgumentException("O motorista ou veículo já está em outra rota ativa.");
        }

        // preciso realaviar isso aqui de outro modo (eu acho ://///)
        // pathData = DijkstraService(route.getOrigin(), route.getDestination());
        // route.setPath = pathData.getPath();
        // route.setTotalDistance = pathData.getTotalDistance();

        routeDAO.save(route);
    }

    public void updateRoute(Route route) {
        validateRoute(route);

        routeDAO.update(route);
    }

    public Route findById(Long id) {
        return routeDAO.findById(id);
    }

    public List<Route> findAll() {
        return routeDAO.findAll();
    }

    public void deleteRoute(Long id) {
        Route route = routeDAO.findById(id);
        if (route != null) {
            routeDAO.delete(route);
        } else {
            throw new IllegalArgumentException("Rota não encontrada para exclusão.");
        }
    }

    private void validateRoute(Route route){
        if (route.getDriver() == null || driverService.findById(route.getDriver().getId()) == null) {
            throw new IllegalArgumentException("Motorista inválido.");
        }

        if (route.getVehicle() == null || vehicleService.findById(route.getVehicle().getId()) == null) {
            throw new IllegalArgumentException("Veículo inválido.");
        }

        if (route.getOrigin() == null || locationService.findById(route.getOrigin().getId()) == null) {
            throw new IllegalArgumentException("Origem inválida.");
        }

        if (route.getDestination() == null || locationService.findById(route.getDestination().getId()) == null) {
            throw new IllegalArgumentException("Destino inválido.");
        }

        if (route.getOrigin().getId().equals(route.getDestination().getId())) {
            throw new IllegalArgumentException("Origem e destino não podem ser iguais.");
        }
    }
}
