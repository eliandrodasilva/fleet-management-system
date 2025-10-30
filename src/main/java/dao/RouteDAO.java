package dao;

import model.Route;

public class RouteDAO extends AbstractDAOImpl<Route, Long> {

    public RouteDAO() {}

    public boolean existsByOriginAndDestination(Long originId, Long destinationId) {
        Long count = em.createQuery(
                        "SELECT COUNT(r) FROM Route r " +
                                "WHERE r.origin.id = :originId AND r.destination.id = :destinationId",
                        Long.class
                )
                .setParameter("originId", originId)
                .setParameter("destinationId", destinationId)
                .getSingleResult();

        return count > 0;
    }

}
