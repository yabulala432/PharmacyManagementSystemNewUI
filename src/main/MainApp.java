package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainApp extends JFrame {
    private SidePanel sidePanel;
    private HomePanel homePanel;
    private EmpCrudPanel empCrudPanel;
    private ItemCrudPanel itemCrudPanel;
    private RemovalPanel removalPanel;
    private MePanel mePanel;
    private QuestionsPanel questionsPanel;


    public MainApp() {
        // Set up the main frame
        setTitle("My Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(960, 600);

        // Create the side panel
        sidePanel = new SidePanel();
        add(sidePanel, BorderLayout.WEST);


        // Create the home panel
        homePanel = new HomePanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("src//main/icons/backHome.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

//        homePanel = new HomePanel();
        add(homePanel, BorderLayout.CENTER);

        // Create the empCrud panel
        empCrudPanel = new EmpCrudPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("src//main/icons/empBack.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Add a label to the panel
        JLabel empCrudLabel = new JLabel("EmpCrud", SwingConstants.CENTER);
        empCrudPanel.add(empCrudLabel);

        // Create the itemCrud panel
        itemCrudPanel = new ItemCrudPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("src//main/icons/itemBack.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Add a label to the panel
        JLabel itemCrudLabel = new JLabel("ItemCrud", SwingConstants.CENTER);
        itemCrudPanel.add(itemCrudLabel);

        // Create the removal panel
        removalPanel = new RemovalPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("src//main/icons/rmvBack.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Add a label to the panel
        JLabel removalLabel = new JLabel("Removal", SwingConstants.CENTER);
        removalPanel.add(removalLabel);

        // Create the mePanel
        mePanel = new MePanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("src//main/icons/meBack.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Add a label to the panel
        JLabel meLabel = new JLabel("Me", SwingConstants.CENTER);
        mePanel.add(meLabel);

        // Create the questions panel
        questionsPanel = new QuestionsPanel();
        // Add a label to the panel
        JLabel questionsLabel = new JLabel("Questions", SwingConstants.CENTER);
        questionsPanel.add(questionsLabel);


        // Set up button actions
        homePanel.getItemsButton().addMouseListener(myMouseAdapter(itemCrudPanel, null));
        homePanel.getEmployeeButton().addMouseListener(myMouseAdapter(empCrudPanel, null));
        homePanel.getRemovalsButton().addMouseListener(myMouseAdapter(removalPanel, null));

        sidePanel.getHomeButton().addMouseListener(myMouseAdapter(homePanel, sidePanel.getHomeButton()));
        sidePanel.getMeButton().addMouseListener(myMouseAdapter(mePanel, sidePanel.getMeButton()));
        sidePanel.getEmpCrudButton().addMouseListener(myMouseAdapter(empCrudPanel, sidePanel.getEmpCrudButton()));
        sidePanel.getItemCrudButton().addMouseListener(myMouseAdapter(itemCrudPanel, sidePanel.getItemCrudButton()));
        sidePanel.getRemovalButton().addMouseListener(myMouseAdapter(removalPanel, sidePanel.getRemovalButton()));
        sidePanel.getQuestionsButton().addMouseListener(myMouseAdapter(questionsPanel, sidePanel.getQuestionsButton()));

//        System.out.println(homePanel.getWidth() + "," + homePanel.getHeight());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void showPanel(JPanel panel) {
        remove(getContentPane().getComponent(1));
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void borderOnHover(JLabel label) {
        label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
    }

    private void removeBorder(JLabel label) {
        label.setBorder(null);
    }

    private MouseAdapter myMouseAdapter(JPanel panelToSwitch, JLabel labelToBorder) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPanel(panelToSwitch);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (labelToBorder != null)
                    borderOnHover(labelToBorder);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                if (labelToBorder != null)
                    removeBorder(labelToBorder);
            }
        };
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new splashscreen.SplashScreen(null, true).setVisible(true);
            new MainApp();
        });

    }

    public static String username = "", password = "";
}

