package view.dashboard;

import view.list.ListPanel;
import view.form.FormPanel;
import view.seeder.SeederPanel;

import javax.swing.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Sistema de Gerenciamento de Frotas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane mainTabs = new JTabbedPane();

        mainTabs.addTab("Relatórios e Listagens", new ListPanel());
        mainTabs.addTab("Cadastros", new FormPanel());
//        mainTabs.addTab("Atualizar Informações", new FormPanel());
        mainTabs.addTab("Popular BD", new SeederPanel());

        add(mainTabs);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
    }
}