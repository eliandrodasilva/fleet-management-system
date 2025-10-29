package dao;

import model.VehicleBrand;

public class VehicleBrandDAO extends AbstractDAOImpl<VehicleBrand, Long> {

    public VehicleBrandDAO() {}

    public boolean existsByName(String name) {
        Long count = em.createQuery(
                        "SELECT COUNT(vb) FROM VehicleBrand vb WHERE UPPER(vb.name) = UPPER(:name)", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }
}