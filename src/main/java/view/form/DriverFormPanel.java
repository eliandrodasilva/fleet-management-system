package view.form;

import model.Driver;
import service.DriverService;
import view.base.BaseFormPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DriverFormPanel extends BaseFormPanel {

    private final JTextField txtName = new JTextField(20);
    private final JTextField txtCpf = new JTextField(15);
    private final JTextField txtCnh = new JTextField(15);
    private final DriverService driverService = new DriverService();

    public DriverFormPanel() {
        super();

        addField("Nome Completo:", txtName, 0);
        addField("CPF:", txtCpf, 1);
        addField("CNH:", txtCnh, 2);

        JButton btnSave = new JButton("Salvar Motorista");
        btnSave.addActionListener(this::saveDriver);

        addButton(btnSave, 3);
    }

    private void saveDriver(ActionEvent e) {
        try {
            Driver d = new Driver();
            d.setName(txtName.getText());
            d.setCpf(txtCpf.getText());
            d.setLicenseNumber(txtCnh.getText());

            driverService.createDriver(d);

            JOptionPane.showMessageDialog(this, "Motorista salvo com sucesso!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


