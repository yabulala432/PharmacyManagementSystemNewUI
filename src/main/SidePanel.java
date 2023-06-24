package main;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private JLabel homeButton, empCrudButton, itemCrudButton, removalButton, meButton, questionsButton;

    public SidePanel() {
        setLayout(new GridLayout(6, 1));
        setPreferredSize(new Dimension(100, getHeight()));
        setBackground(Color.LIGHT_GRAY);

        homeButton = new JLabel();
        homeButton.setIcon(new ImageIcon("src/main/icons/home2.png"));
        empCrudButton = new JLabel();
        empCrudButton.setIcon(new ImageIcon("src/main/icons/empIcon.png"));
        itemCrudButton = new JLabel(new ImageIcon("itemCrud.png"));
        itemCrudButton.setIcon(new ImageIcon("src/main/icons/medicine.png"));
        removalButton = new JLabel(new ImageIcon("removal.png"));
        removalButton.setIcon(new ImageIcon("src/main/icons/dustbin.png"));
        meButton = new JLabel(new ImageIcon("me.png"));
        meButton.setIcon(new ImageIcon("src/main/icons/user.png"));
        questionsButton = new JLabel(new ImageIcon("questions.png"));
        questionsButton.setIcon(new ImageIcon("src/main/icons/questions.png"));

        add(homeButton);
        add(empCrudButton);
        add(itemCrudButton);
        add(removalButton);
        add(meButton);
        add(questionsButton);
    }

    public JLabel getHomeButton() {
        return homeButton;
    }

    public JLabel getEmpCrudButton() {
        return empCrudButton;
    }

    public JLabel getItemCrudButton() {
        return itemCrudButton;
    }

    public JLabel getRemovalButton() {
        return removalButton;
    }

    public JLabel getMeButton() {
        return meButton;
    }

    public JLabel getQuestionsButton() {
        return questionsButton;
    }
}
