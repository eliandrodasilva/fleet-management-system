package dao;

import model.Route;

public class RouteDAO extends AbstractDAOImpl<Route, Long>{

    public RouteDAO() {}

    public boolean existsByDriver(Long driverId) {
        return em.createQuery(
                        "SELECT COUNT(r) FROM Route r WHERE r.driver.id = :driverId", Long.class
                )
                .setParameter("driverId", driverId)
                .getSingleResult() > 0;
    }

    public boolean existsByVehicle(Long vehicleId) {
        return em.createQuery(
                        "SELECT COUNT(r) FROM Route r WHERE r.vehicle.id = :vehicleId", Long.class
                )
                .setParameter("vehicleId", vehicleId)
                .getSingleResult() > 0;
    }
}
