package main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class HomePanel extends JPanel {
    public HomePanel() {
        initComponents();
    }

    private void initComponents() {
        imageLabel = new JLabel();
        itemsButton = new JLabel();
        removalsButton = new JLabel();
        employeeButton = new JLabel();

        setBackground(new java.awt.Color(30, 40, 44));

        itemsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/items1.png")))); // NOI18N
        itemsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                itemsButtonMouseEntered(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                itemsButtonMouseReleased(evt);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                itemsButtonMouseExited(evt);
            }
        });

        removalsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/removals1.png")))); // NOI18N
        removalsButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                removalsButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                removalsButtonMouseExited(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                removalsButtonMouseReleased(evt);
            }
        });

        employeeButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/employee1.png")))); // NOI18N
        employeeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                employeeButtonMouseEntered(evt);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                employeeButtonMouseExited(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                employeeButtonMouseReleased(evt);
            }
        });

        //<editor-fold>
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(imageLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(itemsButton, GroupLayout.Alignment.TRAILING)
                                        .addComponent(removalsButton, GroupLayout.Alignment.TRAILING)
                                        .addComponent(employeeButton, GroupLayout.Alignment.TRAILING))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(185, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(removalsButton)
                                                .addGap(29, 29, 29)
                                                .addComponent(employeeButton)
                                                .addGap(34, 34, 34)
                                                .addComponent(itemsButton)
                                                .addGap(246, 246, 246))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
                                                .addGap(83, 83, 83))))
        );// </editor-fold>
    }

    private void removalsButtonMouseReleased(MouseEvent evt) {
        removalsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/removals1.png"))));
    }

    private void itemsButtonMouseReleased(MouseEvent evt) {
        // TODO add your handling code here:
        itemsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/items1.png"))));
    }

    private void employeeButtonMouseReleased(MouseEvent evt) {
        // TODO add your handling code here:
        employeeButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/employee1.png"))));
    }

    private void itemsButtonMouseEntered(MouseEvent evt) {
        // TODO add your handling code here:
        itemsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/items2.png"))));
    }

    private void removalsButtonMouseEntered(MouseEvent evt) {
        // TODO add your handling code here:
        removalsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/removals2.png"))));
    }

    private void employeeButtonMouseEntered(MouseEvent evt) {
        // TODO add your handling code here:
        employeeButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/employee2.png"))));
    }

    private void removalsButtonMouseExited(MouseEvent evt) {
        // TODO add your handling code here:
        removalsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/removals1.png"))));
    }

    private void employeeButtonMouseExited(MouseEvent evt) {
        // TODO add your handling code here:
        employeeButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/employee1.png"))));
    }

    private void itemsButtonMouseExited(MouseEvent evt) {
        // TODO add your handling code here:
        itemsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/items1.png"))));
    }

    // Variables declaration - do not modify
    private static JLabel employeeButton;
    private static JLabel imageLabel;
    private JLabel itemsButton;
    private JLabel removalsButton;

    public JLabel getEmployeeButton() {
        return employeeButton;
    }

    public JLabel getItemsButton() {
        return itemsButton;
    }

    public JLabel getRemovalsButton() {
        return removalsButton;
    }
    // End of variables declaration

}
