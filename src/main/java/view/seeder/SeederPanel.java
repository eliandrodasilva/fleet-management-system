package view.seeder;

import seed.DatabaseSeeder;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class SeederPanel extends JPanel {

    private JTextArea logArea;
    private JButton btnSeed;
    private JProgressBar progressBar;

    public SeederPanel() {
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 63, 65));
        JLabel lblTitle = new JLabel("Gerenciador de Seed do Banco de Dados");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerPanel.add(lblTitle);
        add(headerPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        logArea.setBackground(new Color(43, 43, 43));
        logArea.setForeground(new Color(169, 183, 198));

        DefaultCaret caret = (DefaultCaret) logArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Logs de Execução"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Aguardando início...");

        btnSeed = new JButton("Popular Banco de Dados");
        btnSeed.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSeed.addActionListener(e -> executeSeed());

        bottomPanel.add(progressBar, BorderLayout.NORTH);
        bottomPanel.add(btnSeed, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void executeSeed() {
        btnSeed.setEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setString("Rodando Seeds...");
        logArea.setText("");

        SwingWorker<Void, String> worker = new SwingWorker<>() {
            PrintStream originalOut = System.out;

            @Override
            protected Void doInBackground() throws Exception {
                System.setOut(new PrintStream(new TextAreaOutputStream(logArea)));
                try {
                    publish("--- INICIANDO ---");
                    DatabaseSeeder seeder = new DatabaseSeeder();
                    seeder.seedAll();
                    publish("--- CONCLUÍDO ---");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                System.setOut(originalOut);
                btnSeed.setEnabled(true);
                progressBar.setIndeterminate(false);
                progressBar.setString("Concluído!");
                JOptionPane.showMessageDialog(SeederPanel.this, "Processo finalizado!");
            }
        };
        worker.execute();
    }

    private static class TextAreaOutputStream extends OutputStream {
        private final JTextArea textArea;
        public TextAreaOutputStream(JTextArea textArea) { this.textArea = textArea; }
        @Override
        public void write(int b) {
            SwingUtilities.invokeLater(() -> textArea.append(String.valueOf((char) b)));
        }
        @Override
        public void write(byte[] b, int off, int len) {
            String text = new String(b, off, len);
            SwingUtilities.invokeLater(() -> textArea.append(text));
        }
    }
}