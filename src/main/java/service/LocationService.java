package service;

import dao.LocationDAO;
import model.Location;

import java.util.List;

public class LocationService {

    private final LocationDAO locationDAO = new LocationDAO();

    public void createLocation(Location location) {
        validateLocation(location);
        locationDAO.save(location);
    }

    public void updateLocation(Location location) {
        validateLocation(location);
        locationDAO.update(location);
    }

    public Location findById(Long id) {
        return locationDAO.findById(id);
    }

    public List<Location> findAll() {
        return locationDAO.findAll();
    }

    public void deleteLocation(Long id) {
        Location location = locationDAO.findById(id);
        if (location != null) {
            locationDAO.delete(location);
        } else {
            throw new IllegalArgumentException("Local não encontrado para exclusão.");
        }
    }

    private void validateLocation(Location location) {
        if (location.getName() == null || location.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do local é obrigatório.");
        }

        if (location.getName().length() < 3) {
            throw new IllegalArgumentException("O nome do local deve ter pelo menos 3 caracteres.");
        }

        if (locationDAO.existsByName(location.getName())) {
            throw new IllegalArgumentException("Já existe um local com esse nome: " + location.getName());
        }
    }
}