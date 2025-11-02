package service;

import dao.DriverDAO;
import model.Driver;

import java.util.List;

public class DriverService {

    private DriverDAO driverDAO = new DriverDAO();

    public void createDriver(Driver driver) {
        driver.validate();
        driverDAO.save(driver);
    }

    public Driver updateDriver(Driver driver) {
        Driver existing = driverDAO.findById(driver.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Motorista não encontrado");
        }

        driver.validate();

        return driverDAO.update(driver);
    }

    public void deleteDriver(Long id) {
        Driver driver = driverDAO.findById(id);
        if (driver != null) {
            driverDAO.delete(driver);
        }
    }

    public List<Driver> findAll() {
        return driverDAO.findAll();
    }

    public Driver findById(Long id) {
        return driverDAO.findById(id);
    }

    public Driver findByCPF(String cpf) {
        return driverDAO.findByCPF(cpf);
    }

    public Driver findByLicenseNumber(String license) {
        return driverDAO.findByLicenseNumber(license);
    }
}
