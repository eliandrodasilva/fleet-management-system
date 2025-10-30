package service;

import dao.RouteDAO;
import model.Route;

import java.math.BigDecimal;
import java.util.List;

public class RouteService {

    private final RouteDAO routeDAO = new RouteDAO();
    private final LocationService locationService = new LocationService();

    public void createRoute(Route route) {
        validateRoute(route);

        if (routeDAO.existsByOriginAndDestination(route.getOrigin().getId(), route.getDestination().getId())) {
            throw new IllegalArgumentException("Já existe uma rota cadastrada entre estas localidades.");
        }

        routeDAO.save(route);
    }

    public void updateRoute(Route route) {
        validateRoute(route);

        List<Route> allRoutes = routeDAO.findAll();
        boolean exists = allRoutes.stream()
                .anyMatch(r -> !r.getId().equals(route.getId()) &&
                        r.getOrigin().getId().equals(route.getOrigin().getId()) &&
                        r.getDestination().getId().equals(route.getDestination().getId()));

        if (exists) {
            throw new IllegalArgumentException("Já existe outra rota entre estas localidades.");
        }

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

    private void validateRoute(Route route) {
        if (route.getOrigin() == null || locationService.findById(route.getOrigin().getId()) == null) {
            throw new IllegalArgumentException("A origem da rota deve ser uma localização válida.");
        }

        if (route.getDestination() == null || locationService.findById(route.getDestination().getId()) == null) {
            throw new IllegalArgumentException("O destino da rota deve ser uma localização válida.");
        }

        if (route.getOrigin().getId().equals(route.getDestination().getId())) {
            throw new IllegalArgumentException("Origem e destino não podem ser iguais.");
        }

        if (route.getDistance() == null || route.getDistance().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A distância da rota deve ser maior que zero.");
        }
    }
}
