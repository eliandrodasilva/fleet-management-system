package service;

import dao.VehicleModelDAO;
import model.VehicleBrand;
import model.VehicleModel;

import java.util.List;

public class VehicleModelService {

    private final VehicleModelDAO vehicleModelDAO = new VehicleModelDAO();
    private final VehicleBrandService vehicleBrandService = new VehicleBrandService();

    public void createVehicleModel(VehicleModel model) {
        validateVehicleModel(model);

        if (vehicleModelDAO.existsByNameAndBrand(model.getName(), model.getBrand().getId())) {
            throw new IllegalArgumentException(
                    "Já existe um modelo com o nome '" + model.getName() + "' para a marca '" + model.getBrand().getName() + "'."
            );
        }

        vehicleModelDAO.save(model);
    }

    public void updateVehicleModel(VehicleModel model) {
        validateVehicleModel(model);

        List<VehicleModel> allModels = vehicleModelDAO.findAll();
        boolean exists = allModels.stream()
                .anyMatch(m -> !m.getId().equals(model.getId()) &&
                        m.getName().equalsIgnoreCase(model.getName()) &&
                        m.getBrand().getId().equals(model.getBrand().getId()));

        if (exists) {
            throw new IllegalArgumentException(
                    "Já existe outro modelo com o nome '" + model.getName() + "' para a marca '" + model.getBrand().getName() + "'."
            );
        }

        vehicleModelDAO.update(model);
    }

    public VehicleModel findById(Long id) {
        return vehicleModelDAO.findById(id);
    }

    public List<VehicleModel> findAll() {
        return vehicleModelDAO.findAll();
    }

    public void deleteVehicleModel(Long id) {
        VehicleModel model = vehicleModelDAO.findById(id);
        if (model == null) {
            throw new IllegalArgumentException("Modelo não encontrado para exclusão.");
        }
        vehicleModelDAO.delete(model);
    }

    private void validateVehicleModel(VehicleModel model) {
        if (model.getName() == null || model.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do modelo é obrigatório.");
        }

        if (model.getBrand() == null || model.getBrand().getId() == null) {
            throw new IllegalArgumentException("O modelo deve estar associado a uma marca válida.");
        }

        VehicleBrand brand = vehicleBrandService.findById(model.getBrand().getId());
        if (brand == null) {
            throw new IllegalArgumentException("A marca associada não existe.");
        }

        if (model.getName().length() < 2) {
            throw new IllegalArgumentException("O nome do modelo deve ter pelo menos 2 caracteres.");
        }
    }
}
