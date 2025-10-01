package dao;

import model.Vehicle;

import java.util.List;

public class VehicleDAO extends AbstractDAOImpl<Vehicle, Long> {

    public VehicleDAO() {
        super(Vehicle.class);
    }

    public Vehicle findByLicensePlate(String licensePlate) {
        List<Vehicle> list = em.createQuery(
                        "FROM Vehicle v WHERE v.licensePlate = :plate", Vehicle.class)
                .setParameter("plate", licensePlate)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Vehicle> findByStatus(String status) {
        return em.createQuery(
                        "FROM Vehicle v WHERE v.status = :status", Vehicle.class)
                .setParameter("status", status)
                .getResultList();
    }
}
