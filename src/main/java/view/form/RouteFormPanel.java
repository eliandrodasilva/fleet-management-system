package view.form;

import model.*;
import service.DriverService;
import service.LocationService;
import service.RouteService;
import service.VehicleService;
import view.base.BaseFormPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RouteFormPanel extends BaseFormPanel {

    private final JComboBox<Driver> comboDriver = new JComboBox<>();
    private final JComboBox<Vehicle> comboVehicle = new JComboBox<>();
    private final JComboBox<Location> comboOrigin = new JComboBox<>();
    private final JComboBox<Location> comboDestination = new JComboBox<>();

    private final DriverService driverService = new DriverService();
    private final VehicleService vehicleService = new VehicleService();
    private final LocationService locationService = new LocationService();
    private final RouteService routeService = new RouteService();

    public RouteFormPanel() {
        super();

        comboDriver.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Driver driver) {
                    setText("[ID "+  driver.getId() + "] - " + driver.getName());
                }
                return this;
            }
        });

        comboVehicle.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Vehicle vehicle) {
                    setText("[ID "+ vehicle.getId() + "] - " + vehicle.getModel().getName());
                }
                return this;
            }
        });

        comboOrigin.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Location location) {
                    setText("[ID "+ location.getId() + "] - " + location.getName());
                }
                return this;
            }
        });

        comboDestination.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Location location) {
                    setText("[ID "+ location.getId() + "] - " + location.getName());
                }
                return this;
            }
        });

        loadDrivers();
        loadVehicles();
        loadLocations();

        addField("Motorista:", comboDriver, 0);
        addField("Veículo:", comboVehicle, 1);
        addField("Origem:", comboOrigin, 2);
        addField("Destino:", comboDestination, 3);

        JButton btnSave = new JButton("Salvar Rota");
        btnSave.addActionListener(this::saveRoute);
        addButton(btnSave, 5);
    }

    private void loadDrivers() {
        comboDriver.removeAllItems();
        List<Driver> drivers = driverService.findAll();
        for (Driver d : drivers) {
            comboDriver.addItem(d);
        }
    }

    private void loadVehicles() {
        comboVehicle.removeAllItems();
        List<Vehicle> vehicles = vehicleService.findAll();
        for (Vehicle v : vehicles) {
            comboVehicle.addItem(v);
        }
    }

    private void loadLocations() {
        comboOrigin.removeAllItems();
        comboDestination.removeAllItems();
        List<Location> locations = locationService.findAll();
        for (Location l : locations) {
            comboOrigin.addItem(l);
            comboDestination.addItem(l);
        }
    }

    private void saveRoute(ActionEvent e) {
        try {
            Route r = new Route();
            r.setDriver((Driver) comboDriver.getSelectedItem());
            r.setVehicle((Vehicle) comboVehicle.getSelectedItem());
            r.setOrigin((Location) comboOrigin.getSelectedItem());
            r.setDestination((Location) comboDestination.getSelectedItem());

            routeService.createRoute(r);

            JOptionPane.showMessageDialog(this, "Rota salva com sucesso!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
