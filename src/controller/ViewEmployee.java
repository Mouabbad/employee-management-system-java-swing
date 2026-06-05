package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;

import net.proteanit.sql.DbUtils;

public class ViewEmployee extends JFrame implements ActionListener {

    private JTable table;
    private JComboBox<String> employeeCombo;
    private JButton printBtn, updateBtn, backBtn, viewProfileBtn, gererCongeBtn, supprimerBtn;


    public ViewEmployee() {
        setTitle("Employee Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 820);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMain(), BorderLayout.CENTER);

        loadEmployeeNames();
        loadAllEmployees();
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#87CEFA"));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        panel.add(title);
        return panel;
    }

    private JPanel createMain() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(new EmptyBorder(20, 30, 30, 30));
        main.setBackground(new Color(250, 251, 252));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(10, 10, 10, 10)));

        JLabel label = new JLabel("Nom de l'employé:");
        label.setForeground(Color.BLUE);
        top.add(label, BorderLayout.WEST);

        employeeCombo = new JComboBox<>();
        employeeCombo.setPreferredSize(new Dimension(200, 30));
        employeeCombo.addActionListener(e -> {
            String nom = (String) employeeCombo.getSelectedItem();
            if (nom != null && !nom.trim().isEmpty()) {
                filterByNom(nom.trim());
            }
        });
        top.add(employeeCombo, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        printBtn = createBtn("Imprimer", new Color(108, 117, 125));
        updateBtn = createBtn("Modifier", new Color(40, 167, 69));
        viewProfileBtn = createBtn("Voir Profil", new Color(0, 123, 255));
        gererCongeBtn = createBtn("Gérer Congé", new Color(255, 193, 7));
        
        supprimerBtn = createBtn("Supprimer", new Color(200, 0, 0));  // bouton rouge
        backBtn = createBtn("Retour", new Color(220, 53, 69));

        buttonPanel.add(printBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(viewProfileBtn);
        buttonPanel.add(gererCongeBtn);
        buttonPanel.add(supprimerBtn);
        buttonPanel.add(backBtn);
        

        top.add(buttonPanel, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);

        table = new JTable();
        table.setRowHeight(35);
        table.setSelectionBackground(new Color(230, 240, 255));
        table.setDefaultEditor(Object.class, null); // read-only
        table.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new LineBorder(Color.GRAY));
        main.add(scroll, BorderLayout.CENTER);

        return main;
    }

    private JButton createBtn(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.addActionListener(this);
        return btn;
    }

    private void loadEmployeeNames() {
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT DISTINCT nom FROM employe ORDER BY nom");
            while (rs.next()) {
                employeeCombo.addItem(rs.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllEmployees() {
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM employe WHERE statut = 'actif'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterByNom(String nom) {
        try {
            conn c = new conn();
            String sql = "SELECT * FROM employe WHERE LOWER(nom) = LOWER('" + nom + "')";
            ResultSet rs = c.s.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            if (table.getRowCount() > 0) {
                table.setRowSelectionInterval(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        if (e.getSource() == backBtn) {
            setVisible(false);
            new dashbordAdmin().setVisible(true);
            return;
        }

        if (e.getSource() == printBtn) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un employé dans le tableau.");
            return;
        }

        String id = table.getValueAt(row, 0).toString();
if (e.getSource() == updateBtn) {
    setVisible(false);
    new UpdateEmployee(id).setVisible(true);
} else if (e.getSource() == viewProfileBtn) {
    setVisible(false);
    new ProfileEmployee(id).setVisible(true);
} else if (e.getSource() == gererCongeBtn) {
    new GererConges(id).setVisible(true);
} else if (e.getSource() == supprimerBtn) {
    setVisible(false);
    new RemoveEmployee(id).setVisible(true);
}
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewEmployee().setVisible(true));
    }
}