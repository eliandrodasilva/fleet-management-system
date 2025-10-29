package dao;

import model.VehicleModel;

public class VehicleModelDAO extends AbstractDAOImpl<VehicleModel, Long> {

    public VehicleModelDAO() {}

    public boolean existsByNameAndBrand(String name, Long brandId) {
        Long count = em.createQuery(
                        "SELECT COUNT(vm) FROM VehicleModel vm " +
                                "WHERE UPPER(vm.name) = UPPER(:name) AND vm.brand.id = :brandId",
                        Long.class
                )
                .setParameter("name", name.trim().toUpperCase())
                .setParameter("brandId", brandId)
                .getSingleResult();

        return count > 0;
    }
}
