package dao;

import model.Driver;

import java.util.List;

public class DriverDAO extends AbstractDAOImpl<Driver, Long> {

    public DriverDAO() {}

    public Driver findByCPF(String cpf) {
        List<Driver> list = em.createQuery(
                        "FROM Driver d WHERE d.cpf = :cpf", Driver.class)
                .setParameter("cpf", cpf)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public Driver findByLicenseNumber(String licenseNumber) {
        List<Driver> list = em.createQuery(
                        "FROM Driver d WHERE d.licenseNumber = :license", Driver.class)
                .setParameter("license", licenseNumber)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
