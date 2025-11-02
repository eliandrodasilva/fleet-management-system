package service;

import dao.VehicleBrandDAO;
import model.VehicleBrand;

import java.util.List;

public class VehicleBrandService {

    private final VehicleBrandDAO vehicleBrandDAO = new VehicleBrandDAO();

    public void createVehicleBrand(VehicleBrand brand) {
        validateVehicleBrand(brand);

        if (vehicleBrandDAO.existsByName(brand.getName())) {
            throw new IllegalArgumentException("Já existe uma marca com esse nome: " + brand.getName());
        }

        vehicleBrandDAO.save(brand);
    }

    public void updateVehicleBrand(VehicleBrand brand) {
        validateVehicleBrand(brand);

        List<VehicleBrand> allBrands = vehicleBrandDAO.findAll();
        boolean nameExists = allBrands.stream()
                .anyMatch(b -> !b.getId().equals(brand.getId()) &&
                        b.getName().equalsIgnoreCase(brand.getName()));

        if (nameExists) {
            throw new IllegalArgumentException("Já existe outra marca com o mesmo nome.");
        }

        vehicleBrandDAO.update(brand);
    }

    public VehicleBrand findById(Long id) {
        return vehicleBrandDAO.findById(id);
    }

    public List<VehicleBrand> findAll() {
        return vehicleBrandDAO.findAll();
    }

    public void deleteVehicleBrand(Long id) {
        VehicleBrand brand = vehicleBrandDAO.findById(id);
        if (brand != null) {
            vehicleBrandDAO.delete(brand);
        } else {
            throw new IllegalArgumentException("Marca não encontrada para exclusão.");
        }
    }

    private void validateVehicleBrand(VehicleBrand brand) {
        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da marca é obrigatório.");
        }

        if (brand.getName().length() < 2) {
            throw new IllegalArgumentException("O nome da marca deve ter pelo menos 2 caracteres.");
        }
    }
}