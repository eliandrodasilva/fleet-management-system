package dao;

import model.Location;

import java.util.List;

public class LocationDAO extends  AbstractDAOImpl<Location, Long> {

    public LocationDAO() {}

    public boolean existsByName(String name) {
        Long count = em.createQuery(
                "SELECT COUNT(l) FROM Location l WHERE LOWER(l.name) = LOWER(:name)", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }

    public List<Location> findByName(String name) {
        return em.createQuery(
                "FROM Location l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%'))", Location.class)
                .setParameter("name", name)
                .getResultList();

    }
}
