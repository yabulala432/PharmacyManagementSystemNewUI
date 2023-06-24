package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import DatabaseLogics.Admin;


/**
 * @author yeab
 */
public class EmpCrudPanel extends JPanel {

    public EmpCrudPanel() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        deleteButton = new JLabel();
        updateButton = new JLabel();
        addButton = new JLabel();
        hoursField = new JTextField();
        usernameLabel = new JLabel();
        nameField = new JTextField();
        nameLabel = new JLabel();
        usernameField = new JTextField();
        hoursLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        empTable = new JTable();

        deleteButton.setIcon(new ImageIcon("src/main/icons/delete.png")); // NOI18N
        updateButton.setIcon(new ImageIcon("src/main/icons/update.png")); // NOI18N
        addButton.setIcon(new ImageIcon("src/main/icons/add.png")); // NOI18N

        usernameLabel.setText("Username :");
        nameLabel.setText("Name :");
        hoursLabel.setText("Hours :");
        try {
            admin = new Admin("YeabsiraYonas", "12345678");
        } catch (SQLException ignored) {
        }
        setEmpTable();
        empTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() || empTable.getSelectedRow() == -1) {
                return;
            }
            int selectedRow = empTable.getSelectedRow();

            String name = (String) empTable.getValueAt(selectedRow, 0);
            String username = (String) empTable.getValueAt(selectedRow, 1);
            String hours = (String) empTable.getValueAt(selectedRow, 2);

            nameField.setText(name);
            usernameField.setText(username);
            hoursField.setText(hours);
        });



        jScrollPane1.setViewportView(empTable);

//<editor-fold defaultstate="collapsed">
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
                                .addGap(514, 540, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel, GroupLayout.Alignment.TRAILING)
                                        .addComponent(usernameLabel, GroupLayout.Alignment.TRAILING)
                                        .addComponent(hoursLabel, GroupLayout.Alignment.TRAILING))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(hoursField, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(nameField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(addButton)
                                                .addGap(73, 73, 73)
                                                .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                .addGap(89, 89, 89))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(updateButton)
                                                .addGap(186, 186, 186))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(hoursField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(hoursLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(usernameLabel)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addButton)
                                                        .addComponent(deleteButton))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(updateButton)))
                                .addGap(280, 280, 280))
        );
//</editor-fold>

        deleteButton.addMouseListener(myMouseListener(deleteButton, "src/main/icons/delete"));
        addButton.addMouseListener(myMouseListener(addButton, "src/main/icons/add"));
        updateButton.addMouseListener(myMouseListener(updateButton, "src/main/icons/update"));
    }
// </editor-fold>

    private MouseListener myMouseListener(JLabel label, String icons) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                int hours = Integer.parseInt(hoursField.getText());
                String defaultPassword = "12345678";

                if(label == addButton){
                    try {
                        admin.addEmployee(name,username,defaultPassword,hours);
                        setEmpTable();
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(null,"Unsuccessful !!!");
                    }
                }else if(label == updateButton){
                    if(admin.updateEmpHoursAndName(username,hours,name)){
                        setEmpTable();
                    }else{
                        JOptionPane.showMessageDialog(null,"Not Updated");
                    }

                }else if(label == deleteButton){
                    String reason = JOptionPane.showInputDialog("Your reason To remove the item pleas ? ");
                    try {
                        if (reason.length() > 1) {
                            if(admin.removeEmployee(username, reason)){
                                setEmpTable();
                            }
                        } else
                            JOptionPane.showMessageDialog(null, "not a sufficient reason to remove an item from the data base");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setIcon(new ImageIcon(icons + "2.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setIcon(new ImageIcon(icons + ".png"));
            }
        };
    }


    // Variables declaration - do not modify
//    <editor-fold>
    private Admin admin;
    private JLabel usernameLabel;
    private JLabel deleteButton;
    private JLabel updateButton;
    private JLabel addButton;
    private JLabel nameLabel;
    private JLabel hoursLabel;
    private JScrollPane jScrollPane1;
    private JTable empTable;
    private JTextField hoursField;
    private JTextField nameField;
    private JTextField usernameField;
//    </editor-fold>
    // End of variables declaration

    private void setEmpTable() {
        ArrayList<String[]> itemsInfo;
        String[][] info;
        try {
            itemsInfo = admin.viewEmployee();
            info = new String[itemsInfo.size()][4];
            for (int i = 0; i < itemsInfo.size(); i++) {
                int l = 0;
                for (int j = 0; j < 5; j++) {
                    if (j == 2) {
                        continue;
                    }
                    info[i][l++] = itemsInfo.get(i)[j];
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        empTable.setModel(new DefaultTableModel(info, new String[]
                {"<html><big><b><i>Name</i></b></big></html>",
                        "<html><big><b><i>Username</b></big></html>",
                        "<html><big><b><i>Hours</b></big></html>",
                        "<html><big><b><i>Wage</b></big></html>"}
        ) {
        });
    }
}

