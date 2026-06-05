package controller;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

public class RemoveEmployee extends JFrame implements ActionListener {

    private Choice cEmpId;
    private JButton deleteBtn, backBtn;
    private JLabel lblname, lblphone, lblemail;
    private JPanel mainPanel, infoPanel, buttonPanel;

    public RemoveEmployee(String empId) {
        initializeUI();

        // Ajouter uniquement l'ID reçu
        cEmpId.add(empId);
        updateEmployeeInfo(empId);

        setVisible(true);
    }

    private void initializeUI() {
        setTitle("Suppression d'employé");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240));

        mainPanel = new JPanel();
        mainPanel.setBounds(20, 20, 860, 440);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        mainPanel.setLayout(null);
        add(mainPanel);

        JLabel heading = new JLabel("Supprimer un employé");
        heading.setBounds(0, 10, 820, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(new Color(204, 0, 0));
        mainPanel.add(heading);

        JPanel selectPanel = new JPanel();
        selectPanel.setBounds(50, 60, 400, 60);
        selectPanel.setLayout(null);
        selectPanel.setOpaque(false);
        mainPanel.add(selectPanel);

        JLabel labelempId = new JLabel("ID Employé:");
        labelempId.setBounds(0, 10, 100, 30);
        labelempId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        selectPanel.add(labelempId);

        cEmpId = new Choice();
        cEmpId.setBounds(110, 10, 250, 30);
        cEmpId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        selectPanel.add(cEmpId);

        infoPanel = createInfoPanel();
        mainPanel.add(infoPanel);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 350, 300, 50);
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
        buttonPanel.setOpaque(false);
        mainPanel.add(buttonPanel);

        deleteBtn = createButton("Supprimer", new Color(204, 0, 0));
        backBtn = createButton("Retour", new Color(0, 102, 204));

        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/delete1.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(500, 100, 350, 250);
        mainPanel.add(imageLabel);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(50, 130, 400, 180);
        panel.setLayout(null);
        panel.setBackground(new Color(249, 249, 249));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            "Informations employé", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), new Color(0, 102, 204))
        );

        addInfoField(panel, "Nom:", 20, 40, lblname = new JLabel());
        addInfoField(panel, "Téléphone:", 20, 80, lblphone = new JLabel());
        addInfoField(panel, "Email:", 20, 120, lblemail = new JLabel());

        return panel;
    }

    private void addInfoField(JPanel panel, String labelText, int x, int y, JLabel valueLabel) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 80, 25);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);

        valueLabel.setBounds(x + 90, y, 250, 25);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        valueLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        valueLabel.setOpaque(true);
        valueLabel.setBackground(Color.WHITE);
        panel.add(valueLabel);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.addActionListener(this);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void updateEmployeeInfo(String empId) {
        try {
            conn c = new conn();
            String query = "SELECT nom, telephone, email FROM employe WHERE id = '" + empId + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                lblname.setText(rs.getString("nom"));
                lblphone.setText(rs.getString("telephone"));
                lblemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des informations employé: " + e.getMessage(),
                "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteBtn) {
            deleteEmployee();
        } else if (ae.getSource() == backBtn) {
            returnToHome();
        }
    }

    private void deleteEmployee() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Êtes-vous sûr de vouloir supprimer cet employé?\nCette action est irréversible.",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                conn c = new conn();
                String query = "UPDATE employe SET statut = 'supprimé' WHERE id = '" + cEmpId.getSelectedItem() + "'";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Employé supprimé avec succès",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);

                this.dispose();
                new ViewEmployee().setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression: " + e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void returnToHome() {
        this.dispose();
        new ViewEmployee().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RemoveEmployee("1"); // valeur par défaut de test
        });
    }
}