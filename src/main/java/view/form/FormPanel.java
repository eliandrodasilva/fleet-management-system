package view.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class FormPanel extends JPanel {

    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private JComboBox<String> selector;

    public FormPanel() {
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(240, 240, 240));
        // Linha que separa no topo (talvez tirar isso aqui sla)
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblSelect = new JLabel("O que deseja cadastrar?");
        lblSelect.setFont(new Font("Segoe UI", Font.BOLD, 12));

        String[] options = {"Motorista", "Veículo", "Localidade", "Rota"};
        selector = new JComboBox<>(options);
        selector.setPreferredSize(new Dimension(200, 30));

        selector.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedName = (String) e.getItem();
                cardLayout.show(cardsPanel, selectedName);
            }
        });

        topPanel.add(lblSelect);
        topPanel.add(selector);
        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        cardsPanel.add(new DriverFormPanel(), "Motorista");
        cardsPanel.add(new VehicleFormPanel(), "Veículo");
        cardsPanel.add(new LocationFormPanel(), "Localidade");
        cardsPanel.add(new RouteFormPanel(), "Rota");

        add(cardsPanel, BorderLayout.CENTER);
    }
}