package view.form;

import model.Vehicle;
import model.VehicleModel;
import model.enums.VehicleStatus;
import service.VehicleModelService;
import service.VehicleService;
import view.base.BaseFormPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VehicleFormPanel extends BaseFormPanel {

    private final JTextField txtPlate = new JTextField(10);
    private final JTextField txtYear = new JTextField(5);
    private final JTextField txtMileage = new JTextField(10);

    private final JComboBox<VehicleModel> comboModel = new JComboBox<>();
    private final JComboBox<VehicleStatus> comboStatus = new JComboBox<>(VehicleStatus.values());

    private final VehicleService vehicleService = new VehicleService();
    private final VehicleModelService modelService = new VehicleModelService();

    public VehicleFormPanel() {
        super();

        comboModel.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof VehicleModel) {
                    VehicleModel model = (VehicleModel) value;

                    // AQUI é o que apareça na tela em relação dos modelos

                    setText(model.getBrand().getName() + " - " + model.getName());
                }
                return this;
            }
        });

        loadModels(); // Carrega os modelos para o dropdown (usar em rotas isso aqui)

        addField("Placa:", txtPlate, 0);
        addField("Modelo:", comboModel, 1);
        addField("Ano:", txtYear, 2);
        addField("Quilometragem:", txtMileage, 3);
        addField("Status:", comboStatus, 4);

        JButton btnSave = new JButton("Salvar Veículo");
        btnSave.addActionListener(this::saveVehicle);

        // Botão de atualizar lista de modelos (caso cadastre um modelo novo e volte aqui ele não atualiza sozin)
        JButton btnRefresh = new JButton("↻");
        btnRefresh.setToolTipText("Atualizar lista de modelos");
        btnRefresh.addActionListener(e -> loadModels());

        // Adicionando um painelzinho para o botão de salvar e refreshsssss
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSave);
        btnPanel.add(btnRefresh);

        addButton(btnSave, 5);
    }

    private void loadModels() {
        comboModel.removeAllItems();
        List<VehicleModel> models = modelService.findAll();
        for (VehicleModel m : models) {
            comboModel.addItem(m);
        }
    }

    private void saveVehicle(ActionEvent e) {
        try {
            Vehicle v = new Vehicle();
            v.setLicensePlate(txtPlate.getText());
            v.setModelYear(Integer.parseInt(txtYear.getText()));
            v.setCurrentKilometers(Integer.parseInt(txtMileage.getText()));

            v.setModel((VehicleModel) comboModel.getSelectedItem());
            v.setStatus((VehicleStatus) comboStatus.getSelectedItem());

            vehicleService.createVehicle(v);

            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
            clearFields();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Ano e Km devem ser números!", "Erro de Formatação", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}