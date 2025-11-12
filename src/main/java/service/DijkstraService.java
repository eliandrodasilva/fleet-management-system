package service;

import model.Location;
import model.RouteSegment;

import java.math.BigDecimal;
import java.util.List;

public class DijkstraService {

    private final RouteSegmentService routeSegmentService;
    private final RouteService routeService;

    public DijkstraService(RouteSegmentService routeSegmentService, RouteService routeService) {
        this.routeService = routeService;
        this.routeSegmentService = routeSegmentService;
    }

//    public PathResult findShortestPath(Location origin, Location destination) {
//        List<RouteSegment> routeSegments = routeSegmentService.findAll();
//    }

    public static class PathResult {
        private final List<Location> path;
        private final BigDecimal totalDistance;

        public PathResult(List<Location> path, BigDecimal totalDistance) {
            this.path = path;
            this.totalDistance = totalDistance;
        }

        public List<Location> getPath() {
            return path;
        }

        public BigDecimal getTotalDistance() {
            return totalDistance;
        }
    }
}
