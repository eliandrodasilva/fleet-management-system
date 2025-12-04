package view.base;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFormPanel extends JPanel {
    protected GridBagConstraints gbc;

    public BaseFormPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margem entre componentes (comparar com o do driverform)
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    protected void addField(String labelText, JComponent component, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0.1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        add(component, gbc);
    }

    protected void addButton(JButton button, int y) {
        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(button, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
    }

    protected void clearFields() {
        for (Component c : getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                ((JComboBox<?>) c).setSelectedIndex(0);
            }
        }
    }
}
