package view.list;

import model.*;
import service.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListPanel extends JPanel {

    private JComboBox<String> cbEntitySelector;
    private JTable table;
    private DefaultTableModel tableModel;

    private final DriverService driverService = new DriverService();
    private final VehicleService vehicleService = new VehicleService();
    private final VehicleBrandService vehicleBrandService = new VehicleBrandService();
    private final VehicleModelService vehicleModelService = new VehicleModelService();
    private final LocationService locationService = new LocationService();
    private final RouteSegmentService routeSegmentService = new RouteSegmentService();
    private final RouteService routeService = new RouteService();
    private final ScheduledServiceService scheduledService = new ScheduledServiceService();

    public ListPanel() {
        setLayout(new BorderLayout());

        initComponents();
        loadSelectedData(); // Carrega a primeira opção ao abrir
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(new JLabel("Selecione o que deseja listar:"));

        String[] options = {"Motoristas", "Veículos", "Marcas", "Modelos", "Localidades", "Segmentos", "Rotas",
                "Serviços"};
        cbEntitySelector = new JComboBox<>(options);
        cbEntitySelector.addActionListener(e -> loadSelectedData());

        JButton btnRefresh = new JButton("↻ Atualizar");
        btnRefresh.addActionListener(e -> loadSelectedData());

        topPanel.add(cbEntitySelector);
        topPanel.add(btnRefresh);
        add(topPanel, BorderLayout.NORTH);

        // --- TABELA ---
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Pra marcar que é apenas pra ler
            }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadSelectedData() {
        String selection = (String) cbEntitySelector.getSelectedItem();

        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        try {
            switch (selection) {
                case "Motoristas":
                    loadDrivers();
                    break;
                case "Veículos":
                    loadVehicles();
                    break;
                case "Marcas":
                    loadBrands();
                    break;
                case "Modelos":
                    loadModels();
                    break;
                case "Localidades":
                    loadLocations();
                    break;
                case "Segmentos":
                    loadSegments();
                    break;
                case "Rotas":
                    loadRoutes();
                    break;
                case "Serviços":
                    loadServices();
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void loadDrivers() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nome", "CPF", "CNH"});

        List<Driver> list = driverService.findAll();
        for (Driver d : list) {
            tableModel.addRow(new Object[]{d.getId(), d.getName(), d.getCpf(), d.getLicenseNumber()});
        }
    }

    private void loadVehicles() {
        tableModel.setColumnIdentifiers(new String[]{"Placa", "Modelo", "Ano", "Status", "Km"});

        List<Vehicle> list = vehicleService.findAll();
        for (Vehicle v : list) {
            String modelName = (v.getModel() != null)
                    ? v.getModel().getBrand().getName() + " - " + v.getModel().getName()
                    : "N/A";

            tableModel.addRow(new Object[]{
                    v.getLicensePlate(),
                    modelName,
                    v.getModelYear(),
                    v.getStatus(),
                    v.getCurrentKilometers()
            });
        }
    }

    private void loadBrands() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Marca"});

        List<VehicleBrand> list = vehicleBrandService.findAll();
        for (VehicleBrand vb : list) {
            tableModel.addRow(new Object[]{vb.getId(), vb.getName()});
        }
    }

    private void loadModels() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Modelo", "Marca"});

        List<VehicleModel> list = vehicleModelService.findAll();
        for (VehicleModel vm : list) {
            tableModel.addRow(new Object[]{vm.getId(), vm.getName(), vm.getBrand().getName()});
        }
    }

    private void loadLocations() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nome do Local"});

        List<Location> list = locationService.findAll();
        for (Location l : list) {
            tableModel.addRow(new Object[]{l.getId(), l.getName()});
        }
    }

    private void loadSegments() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Origem", "Destino", "Distância"});

        List<RouteSegment> list = routeSegmentService.findAll();
        for (RouteSegment rs : list){
            tableModel.addRow(new Object[]{rs.getId(), rs.getOrigin().getName(), rs.getDestination().getName(),
                    rs.getDistance() + " km"});
        }
    }

    private void loadRoutes() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Motorista", "Veículo", "Origem", "Destino", "Percuso",
                "Distância Total"});

        List<Route> list = routeService.findAll();
        for (Route r : list) {
            tableModel.addRow(new Object[]{r.getId(), r.getDriver().getName(), r.getVehicle().getModel().getName(),
                    r.getOrigin().getName(),
            r.getDestination().getName(), r.getPath(), r.getTotalDistance() + " km"});
        }
    }

    private void loadServices() {
        tableModel.setColumnIdentifiers(new String[]{"ID", "Veículo", "Tipo", "Data", "Custo Estimado", "Descrição",
        "Prioridade", "Status"});

        List<ScheduledService> list = scheduledService.findAll();
        for (ScheduledService ss : list) {
            tableModel.addRow(new Object[]{ss.getId(), ss.getVehicle().getId(), ss.getServiceType(),
                    ss.getScheduledDate(), ss.getEstimatedCost(), ss.getDescription(), ss.getServicePriority(),
                    ss.getServiceStatus()});
        }
    }
}