package service;

import dao.RouteSegmentDAO;
import model.RouteSegment;

import java.math.BigDecimal;
import java.util.List;

public class RouteSegmentService {

    private final RouteSegmentDAO routeSegmentDAO = new RouteSegmentDAO();
    private final LocationService locationService = new LocationService();

    public void createRoute(RouteSegment routeSegment) {
        validateRoute(routeSegment);

        if (routeSegmentDAO.existsByOriginAndDestination(routeSegment.getOrigin().getId(), routeSegment.getDestination().getId())) {
            throw new IllegalArgumentException("Já existe um segmento cadastrado entre estas localidades.");
        }

        routeSegmentDAO.save(routeSegment);
    }

    public void updateRoute(RouteSegment routeSegment) {
        validateRoute(routeSegment);

        List<RouteSegment> allRouteSegments = routeSegmentDAO.findAll();
        boolean exists = allRouteSegments.stream()
                .anyMatch(r -> !r.getId().equals(routeSegment.getId()) &&
                        r.getOrigin().getId().equals(routeSegment.getOrigin().getId()) &&
                        r.getDestination().getId().equals(routeSegment.getDestination().getId()));

        if (exists) {
            throw new IllegalArgumentException("Já existe outro segmento entre estas localidades.");
        }

        routeSegmentDAO.update(routeSegment);
    }

    public RouteSegment findById(Long id) {
        return routeSegmentDAO.findById(id);
    }

    public List<RouteSegment> findAll() {
        return routeSegmentDAO.findAll();
    }

    public void deleteRoute(Long id) {
        RouteSegment routeSegment = routeSegmentDAO.findById(id);
        if (routeSegment != null) {
            routeSegmentDAO.delete(routeSegment);
        } else {
            throw new IllegalArgumentException("Segmento não encontrada para exclusão.");
        }
    }

    private void validateRoute(RouteSegment routeSegment) {
        if (routeSegment.getOrigin() == null || locationService.findById(routeSegment.getOrigin().getId()) == null) {
            throw new IllegalArgumentException("A origem do segmento deve ser uma localização válida.");
        }

        if (routeSegment.getDestination() == null || locationService.findById(routeSegment.getDestination().getId()) == null) {
            throw new IllegalArgumentException("O destino do segmento deve ser uma localização válida.");
        }

        if (routeSegment.getOrigin().getId().equals(routeSegment.getDestination().getId())) {
            throw new IllegalArgumentException("Origem e destino não podem ser iguais.");
        }

        if (routeSegment.getDistance() == null || routeSegment.getDistance().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A distância do segmento deve ser maior que zero.");
        }
    }
}
