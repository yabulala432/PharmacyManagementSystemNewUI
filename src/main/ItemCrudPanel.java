package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import DatabaseLogics.*;


/**
 * @author yeab
 */
public class ItemCrudPanel extends JPanel {
    public ItemCrudPanel() {
        initComponents();
    }

    /**
     * Creates new form empPanel
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        itemsTable = new JTable();
        nameField = new JTextField();
        remainingYearsField = new JTextField();
        priceField = new JTextField();
        amountField = new JTextField();
        jComboBox1 = new JComboBox<>();
        jLabel1 = new JLabel();
        amountLabel = new JLabel();
        priceLabel = new JLabel();
        yearsLabel = new JLabel();
        nameLabel = new JLabel();
        addButton = new JLabel();
        updateButton = new JLabel();
        deleteButton = new JLabel();
        try {
            admin = new Admin("YeabsiraYonas", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setItemsTable();
        jScrollPane1.setViewportView(itemsTable);

        itemsTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() || itemsTable.getSelectedRow() == -1) {
                return;
            }
            int selectedRow = itemsTable.getSelectedRow();
            String name = (String) itemsTable.getValueAt(selectedRow, 0);
            String price = (String) itemsTable.getValueAt(selectedRow, 3);
            String expDate = (String) itemsTable.getValueAt(selectedRow, 2);
            String type = (String) itemsTable.getValueAt(selectedRow, 1);
            String amount = (String) itemsTable.getValueAt(selectedRow, 4);

            nameField.setText(name);
            priceField.setText(price);
            String date = String.valueOf(Date.valueOf(LocalDate.now()));

            String nowYear = String.valueOf(date).substring(0, date.indexOf('-'));
            String expYear = expDate.substring(0, expDate.indexOf('-'));
            int remainingYear = Integer.parseInt(expYear) - Integer.parseInt(nowYear);

            remainingYearsField.setText(String.valueOf(remainingYear));
            jComboBox1.setSelectedItem(type);
            amountField.setText(amount);

        });

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Select", "Medicine/OTC", "Medicine/Prescribed", "Others"}));

        jLabel1.setText("Type :");

        amountLabel.setText("Amount :");

        priceLabel.setText("Price :");

        yearsLabel.setText("Years :");

        nameLabel.setText("Name :");

        addButton.setIcon(new ImageIcon("src/main/icons/add.png")); // NOI18N

        updateButton.setIcon(new ImageIcon("src/main/icons/update.png")); // NOI18N

        deleteButton.setIcon(new ImageIcon("src/main/icons/delete.png")); // NOI18N

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(amountLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(amountField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(yearsLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(remainingYearsField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(nameLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(priceLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(priceField, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addButton)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(93, 93, 93)
                                                                .addComponent(updateButton)))
                                                .addGap(195, 195, 195))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(deleteButton)
                                                .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(remainingYearsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(yearsLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(priceLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(amountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(amountLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(16, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addButton)
                                                .addGap(7, 7, 7)
                                                .addComponent(updateButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteButton)
                                                .addGap(43, 43, 43))))
        );
        addButton.addMouseListener(myMouseListener(addButton, "src/main/icons/add"));
        updateButton.addMouseListener(myMouseListener(updateButton, "src/main/icons/update"));
        deleteButton.addMouseListener(myMouseListener(deleteButton, "src/main/icons/delete"));
    }// </editor-fold>

    private MouseListener myMouseListener(JLabel label, String icons) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = nameField.getText(), type = (String) jComboBox1.getSelectedItem();
                double price = Double.parseDouble(priceField.getText());
                int amount = Integer.parseInt(amountField.getText());
                int rmnYear = Integer.parseInt(remainingYearsField.getText());
                Date expDate = DateManipulation.getDateAfterYears(rmnYear);
                boolean isSafeYear = DateManipulation.isSafeYear(expDate);
                if (label == addButton) {
                    try {
                        if (name != null && !Objects.equals(type, "Select") && amount > 0 && isSafeYear && price > 0) {
                            admin.addItem(name, type, expDate, price, amount);
                            nameField.setText("");
                            priceField.setText("");
                            amountField.setText("");
                            remainingYearsField.setText("");
                            jComboBox1.setSelectedItem("Select");
                            JOptionPane.showMessageDialog(null, "Successful !!!");
                            setItemsTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect Input/s !!!");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (label == updateButton) {
                    try {
                        if (admin.updateItemOverAll(type, expDate, price, amount, name)) {
                            JOptionPane.showMessageDialog(null, "Type, date , price and amount updated successfully !!!");
                            setItemsTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Nothing Updated !!!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "unexpected error occurred !!!");
                    }
                } else if (label == deleteButton) {
                    String reason = JOptionPane.showInputDialog("Your reason To remove the item pleas ? ");
                    try {
                        if (reason.length() > 1) {
//                            System.out.println("reason" + reason);
                            admin.removeItem(name, reason);
                            setItemsTable();
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
    // <editor-fold defaultstate="collapsed" desc="variables">
    private JLabel addButton;
    private JTextField amountField;
    private JLabel amountLabel;
    private JLabel deleteButton;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable itemsTable;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextField priceField;
    private JLabel priceLabel;
    private JTextField remainingYearsField;
    private JLabel updateButton;
    private JLabel yearsLabel;
    private Admin admin;
//    </editor-fold>
    // End of variables declaration

    private void setItemsTable() {
        ArrayList<String[]> itemsInfo;
        String[][] info;
        try {
            itemsInfo = admin.viewItems();
            info = new String[itemsInfo.size()][5];
            for (int i = 0; i < itemsInfo.size(); i++) {
                System.arraycopy(itemsInfo.get(i), 0, info[i], 0, 5);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        itemsTable.setModel(new DefaultTableModel(info, new String[]
                {"<html><big><b><i>Name</i></b></big></html>",
                        "<html><big><b><i>Type</b></big></html>",
                        "<html><big><b><i>Exp</b></big></html>",
                        "<html><big><b><i>Price</b></big></html>",
                        "<html><big><b><i>Amount</b></big></html>"
                }
        ) {});
    }
}
