package service;

import model.Location;
import model.RouteSegment;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DijkstraService {

    private final RouteSegmentService routeSegmentService;

    private static final BigDecimal INFINITY = new BigDecimal("99999999.99");

    public DijkstraService(RouteSegmentService routeSegmentService) {
        this.routeSegmentService = routeSegmentService;
    }

    public PathResult findShortestPath(Location origin, Location destination) {

        List<RouteSegment> routeSegments = routeSegmentService.findAll();

        Map<Location, List<RouteSegment>> graph = new HashMap<>();
        for (RouteSegment routeSegment : routeSegments) {
            graph.computeIfAbsent(routeSegment.getOrigin(), k -> new ArrayList<>()).add(routeSegment);
            graph.computeIfAbsent(routeSegment.getDestination(), k -> new ArrayList<>()).add(new RouteSegment(routeSegment.getDestination(), routeSegment.getOrigin(), routeSegment.getDistance()));
        }

        Map<Location, BigDecimal> distances = new HashMap<>();
        Map<Location, Location> previous = new HashMap<>();
        Set<Location> unvisited = new HashSet<>(graph.keySet());

        for (Location loc : unvisited) {
            distances.put(loc, INFINITY);
        }
        distances.put(origin, BigDecimal.ZERO);

        while (!unvisited.isEmpty()) {
            Location current = unvisited.stream().min(Comparator.comparing(distances::get)).orElseThrow();

            unvisited.remove(current);

            if (current.equals(destination)) break;

            for (RouteSegment routeSegment : graph.getOrDefault(current, Collections.emptyList())) {
                Location neighbor = routeSegment.getDestination();
                if (!unvisited.contains(neighbor)) continue;

                BigDecimal newDist = distances.get(current).add(routeSegment.getDistance());
                if (newDist.compareTo(distances.get(neighbor)) < 0) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                }
            }
        }

        List<Location> path = new ArrayList<>();
        for (Location at = destination; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        BigDecimal totalDistance = distances.get(destination);

        if (totalDistance == null || totalDistance.compareTo(INFINITY) >= 0) {
            throw new IllegalArgumentException("No path found between " + origin.getName() + " and " + destination.getName());
        }

        return new PathResult(path, totalDistance);
    }

    public static class PathResult {
        private final List<Location> path;
        private final BigDecimal totalDistance;

        public PathResult(List<Location> path, BigDecimal totalDistance) {
            this.path = path;
            this.totalDistance = totalDistance;
        }

        public String getPathString() {
            return path.stream().map(Location::getName).collect(Collectors.joining(" -> "));
        }

        public BigDecimal getTotalDistance() {
            return totalDistance;
        }

        @Override
        public String toString() {
            return "ShortestPath{" + "path=" + getPathString() + ", totalDistance=" + totalDistance + '}';
        }
    }
}
