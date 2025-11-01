package dao;

import model.RouteSegment;

public class RouteSegmentDAO extends AbstractDAOImpl<RouteSegment, Long> {

    public RouteSegmentDAO() {
    }

    public boolean existsByOriginAndDestination(Long originId, Long destinationId) {
        Long count = em.createQuery(
                        "SELECT COUNT(r) FROM RouteSegment r " +
                                "WHERE r.origin.id = :originId AND r.destination.id = :destinationId",
                        Long.class
                )
                .setParameter("originId", originId)
                .setParameter("destinationId", destinationId)
                .getSingleResult();

        return count > 0;
    }
}
