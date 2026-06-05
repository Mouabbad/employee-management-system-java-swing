package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JTextField tfusername;
    private JPasswordField pfpassword;
    private JButton login;
    private JCheckBox showPasswordCheckbox; // Ajouter le JCheckBox

    Login() {
        setTitle("Employee Management System - Login");
        setSize(1280, 920);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#87CEEB"));
        panel.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(350, 380));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        card.setOpaque(true);

        JLabel title = new JLabel(" Connexion");
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/cle.png"));

        // Redimensionner l'icône
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionner à 30x30
        icon = new ImageIcon(newImage);

        // Ajouter l'icône au JLabel avec le texte
        title.setIcon(icon);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBounds(65, 20, 200, 35);
        title.setForeground(new Color(50, 50, 80));
        title.setHorizontalAlignment(SwingConstants.LEFT);  // Aligner le texte à gauche
        card.add(title);

        tfusername = new JTextField();
        tfusername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfusername.setBounds(30, 80, 260, 50);
        tfusername.setBorder(BorderFactory.createTitledBorder("Nom d'utilisateur"));
        card.add(tfusername);

        pfpassword = new JPasswordField();
        pfpassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pfpassword.setBounds(30, 150, 260, 50);
        pfpassword.setBorder(BorderFactory.createTitledBorder("Mot de passe"));
        card.add(pfpassword);

        // Créer le JCheckBox pour afficher/masquer le mot de passe
        showPasswordCheckbox = new JCheckBox("Afficher le mot de passe");
        showPasswordCheckbox.setBounds(30, 210, 200, 25);
        showPasswordCheckbox.setBackground(Color.WHITE);
        showPasswordCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckbox.isSelected()) {
                    // Si coché, afficher le mot de passe
                    pfpassword.setEchoChar((char) 0); // Afficher le mot de passe
                } else {
                    // Si décoché, masquer le mot de passe
                    pfpassword.setEchoChar('*'); // Masquer le mot de passe
                }
            }
        });
        card.add(showPasswordCheckbox);

        login = new JButton("SE CONNECTER");
        login.setBounds(30, 250, 260, 50);
        login.setBackground(new Color(0, 123, 255));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Segoe UI", Font.BOLD, 15));
        login.setFocusPainted(false);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login.setBorder(BorderFactory.createLineBorder(new Color(0, 105, 217)));
        login.addActionListener(this);

        login.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                login.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(MouseEvent evt) {
                login.setBackground(new Color(0, 123, 255));
            }
        });
        card.add(login);

        panel.add(card);
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfusername.getText();
            String password = new String(pfpassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Veuillez remplir tous les champs", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn c = new conn();
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement pstmt = c.getConnection().prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                setVisible(false);
                new dashbordAdmin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Nom d'utilisateur ou mot de passe incorrect", 
                    "Erreur d'authentification", 
                    JOptionPane.ERROR_MESSAGE);
                pfpassword.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Une erreur est survenue: " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
