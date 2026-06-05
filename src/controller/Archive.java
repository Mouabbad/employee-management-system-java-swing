package controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;


public class Archive extends JFrame {

    private JTable table;
    private JButton btnRetour;


    public Archive() {
        setTitle("Archives des employés");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Archives des employés", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        table = new JTable();
        loadArchiveData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    
    
         // Panel pour le bouton retour
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        bottomPanel.setBackground(Color.WHITE);

            // Bouton retour
        btnRetour = new JButton("Retour");
        btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRetour.setBackground(new Color(220, 53, 69));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFocusPainted(false);
        btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

          // Action du bouton
        btnRetour.addActionListener(e -> {
            dispose(); // Fermer Archive
            new dashbordAdmin().setVisible(true); // Revenir au dashboard
        });

           // Ajouter au panel et à la fenêtre
        bottomPanel.add(btnRetour);
        add(bottomPanel, BorderLayout.SOUTH);

    }
    private void loadArchiveData() {
        try {
            conn c = new conn(); // Assure-toi que ta classe conn gère bien la connexion
            String query = "SELECT id, nom, prenom, poste, statut FROM employe";
            ResultSet rs = c.s.executeQuery(query);

            TableModel model = DbUtils.resultSetToTableModel(rs);
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur chargement des archives : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Archive().setVisible(true);
        });
    }
}