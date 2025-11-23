package service;

import dao.VehicleDAO;
import model.Vehicle;
import model.enums.VehicleStatus;

import java.time.Year;
import java.util.List;

public class VehicleService {

    private final VehicleDAO vehicleDAO = new VehicleDAO();

    public void createVehicle(Vehicle vehicle) {
        validateLicensePlateUnique(vehicle.getLicensePlate());
        validateModelYear(vehicle.getModelYear());
        validateKilometers(vehicle.getCurrentKilometers());
        vehicleDAO.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle existing = vehicleDAO.findById(vehicle.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }

        // Rule: do not regress mileage
        if (vehicle.getCurrentKilometers() < existing.getCurrentKilometers()) {
            throw new IllegalArgumentException("Não é possível reduzir a quilometragem.");
        }

        // Rule: do not allow invalid year
        validateModelYear(vehicle.getModelYear());

        // Rule: status cannot go directly to IN_USE if it is IN_MAINTENANCE or INACTIVE
        if (vehicle.getStatus() == VehicleStatus.IN_USE &&
                (existing.getStatus() == VehicleStatus.IN_MAINTENANCE || existing.getStatus() == VehicleStatus.INACTIVE)) {
            throw new IllegalArgumentException("Não é permitido colocar o veículo em uso neste status atual.");
        }

        return vehicleDAO.update(vehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleDAO.findById(id);
        if (vehicle != null) {
            vehicleDAO.delete(vehicle);
        }
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }

    public List<Vehicle> findAll() {
        return vehicleDAO.findAll();
    }

    public Vehicle findById(Long id) {
        return vehicleDAO.findById(id);
    }

    public Vehicle findByLicensePlate(String plate) {
        return vehicleDAO.findByLicensePlate(plate);
    }

    public List<Vehicle> findByStatus(String status) {
        return vehicleDAO.findByStatus(status);
    }

    private void validateLicensePlateUnique(String plate) {
        Vehicle existing = vehicleDAO.findByLicensePlate(plate);
        if (existing != null) {
            throw new IllegalArgumentException("Já existe um veículo com esta placa.");
        }
    }

    private void validateKilometers(int km) {
        if (km < 0) {
            throw new IllegalArgumentException("A quilometragem não pode ser negativa.");
        }
    }

    private void validateModelYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1900 || year > currentYear + 1) {
            throw new IllegalArgumentException("Ano do modelo inválido.");
        }
    }
}
