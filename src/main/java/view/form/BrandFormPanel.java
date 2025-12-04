package view.form;

import model.Location;
import model.VehicleBrand;
import service.VehicleBrandService;
import view.base.BaseFormPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BrandFormPanel extends BaseFormPanel {

    private final JTextField txtName = new JTextField(25);

    private final VehicleBrandService vehicleBrandService = new VehicleBrandService();

    public BrandFormPanel() {
        super();

        addField("Nome da marca:", txtName, 0);

        JButton btnSave = new JButton("Salvar Marca");
        btnSave.addActionListener(this::saveBrand);

        addButton(btnSave, 1);
    }

    private void saveBrand(ActionEvent e) {
        try {
            VehicleBrand b = new VehicleBrand();
            b.setName(txtName.getText());

            vehicleBrandService.createVehicleBrand(b);

            JOptionPane.showMessageDialog(this, "Marca salva com sucesso!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
