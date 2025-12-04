package view.form;

import model.Location;
import service.LocationService;
import view.base.BaseFormPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LocationFormPanel extends BaseFormPanel {

    private final JTextField txtName = new JTextField(25);

    private final LocationService locationService = new LocationService();

    public LocationFormPanel() {
        super();

        addField("Nome do Local:", txtName, 0);

        JButton btnSave = new JButton("Salvar Local");
        btnSave.addActionListener(this::saveLocation);

        addButton(btnSave, 1);
    }

    private void saveLocation(ActionEvent e) {
        try {
            Location l = new Location();
            l.setName(txtName.getText());

            locationService.createLocation(l);

            JOptionPane.showMessageDialog(this, "Local salvo com sucesso!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
