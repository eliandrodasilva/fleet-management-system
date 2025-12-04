package view.form;

import model.Driver;
import model.VehicleBrand;
import model.VehicleModel;
import service.VehicleModelService;
import view.base.BaseFormPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModelFormPanel extends BaseFormPanel {

    private final JTextField txtName = new JTextField(25);

    private final JComboBox<VehicleBrand> comboBrand = new JComboBox<>();

    private final VehicleModelService vehicleModelService = new VehicleModelService();

    public ModelFormPanel() {
        super();

        comboBrand.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof VehicleBrand brand) {
                    setText("[ID "+  brand.getId() + "] - " + brand.getName());
                }
                return this;
            }
        });

//        loadBrands();

        addField("Nome do Modelo", txtName, 0);

        JButton btnSave = new JButton("Salvar Modelo");
//        btnSave.addActionListener(this::saveModel);
        addButton(btnSave, 1);

//        private void loadBrands() {
//            comboBrand.removeAllItems();
//        }
//
//        private void saveModel(ActionEvent e) {
//            try {
//                VehicleModel v = new VehicleModel();
//                v.setName(txtName.getText());
//                v.setBrand((VehicleBrand) comboBrand.getSelectedItem());
//
//                vehicleModelService.createVehicleModel(v);
//
//                JOptionPane.showMessageDialog(this, "Modelo salvo com sucesso!");
//                clearFields();
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }
}
