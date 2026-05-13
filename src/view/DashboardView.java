package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardView extends JFrame {

    private final List<Double> energyData = new ArrayList<>();
    private final DefaultListModel<String> notificationModel = new DefaultListModel<>();

    private final JLabel totalUsageLabel = new JLabel("Total Usage: 0.00 kWh");
    private final JLabel costLabel = new JLabel("Estimated Cost: £0.00");

    private JPanel graphPanel;

    public DashboardView() {

        setTitle("Smart Home Energy Management System - Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        energyData.add(2.5);
        energyData.add(3.8);
        energyData.add(4.2);
        energyData.add(5.5);
        energyData.add(6.1);

        add(createHeader(), BorderLayout.NORTH);
        add(createSidePanel(), BorderLayout.WEST);

        graphPanel = createGraphPanel();
        add(graphPanel, BorderLayout.CENTER);

        add(createNotificationPanel(), BorderLayout.EAST);
        add(createAdminPanel(), BorderLayout.SOUTH);

        addNotification("Dashboard opened successfully.");
        addNotification("Admin logged into SHEMS system.");

        updateDashboard();

        setVisible(true);
    }

    private JPanel createHeader() {

        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 90, 140));

        JLabel title = new JLabel("SHEMS Real-Time Energy Dashboard");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title);

        return panel;
    }

    private JPanel createSidePanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setPreferredSize(new Dimension(230, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Appliance Control"));

        JButton lightButton = new JButton("Toggle Light");
        JButton acButton = new JButton("Toggle AC");
        JButton fridgeButton = new JButton("Check Fridge");
        JButton scheduleButton = new JButton("Schedule Appliance");
        JButton usageButton = new JButton("Add Energy Usage");
        JButton faultButton = new JButton("Report Fault");

        lightButton.addActionListener(e ->
                addNotification("Light status changed.")
        );

        acButton.addActionListener(e ->
                addNotification("Air Conditioner status changed.")
        );

        fridgeButton.addActionListener(e ->
                addNotification("Fridge is running normally.")
        );

        scheduleButton.addActionListener(e ->
                addNotification("Appliance scheduled successfully.")
        );

        usageButton.addActionListener(e ->
                addEnergyUsage()
        );

        faultButton.addActionListener(e ->
                addNotification("Technician alert: appliance fault reported.")
        );

        panel.add(lightButton);
        panel.add(acButton);
        panel.add(fridgeButton);
        panel.add(scheduleButton);
        panel.add(usageButton);
        panel.add(faultButton);
        panel.add(totalUsageLabel);

        return panel;
    }

    private JPanel createGraphPanel() {

        return new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString("Energy Usage Graph", 30, 30);

                int startX = 60;
                int startY = 430;
                int barWidth = 55;
                int gap = 30;

                g.drawLine(startX, startY, startX + 650, startY);
                g.drawLine(startX, startY, startX, 80);

                for (int i = 0; i < energyData.size(); i++) {

                    int barHeight = (int) (energyData.get(i) * 40);
                    int x = startX + 40 + i * (barWidth + gap);
                    int y = startY - barHeight;

                    g.fillRect(x, y, barWidth, barHeight);
                    g.drawString(energyData.get(i) + " kWh", x, y - 10);
                    g.drawString("T" + (i + 1), x + 15, startY + 20);
                }

                g.setFont(new Font("Arial", Font.PLAIN, 14));
                g.drawString("Time Period", 320, 480);
                g.drawString("Usage", 15, 250);
            }
        };
    }

    private JPanel createNotificationPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(270, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Notifications"));

        JList<String> notificationList = new JList<>(notificationModel);
        panel.add(new JScrollPane(notificationList), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAdminPanel() {

        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Admin Panel"));

        JComboBox<String> pricingBox = new JComboBox<>(
                new String[]{
                        "Flat Rate",
                        "Peak Hour Pricing",
                        "Green Energy Discount"
                }
        );

        JButton applyPricing = new JButton("Apply Pricing");
        JButton manageUsers = new JButton("Manage Users");
        JButton securityButton = new JButton("Security Check");
        JButton logoutButton = new JButton("Logout");

        applyPricing.addActionListener(e ->
                addNotification("Pricing strategy changed to: "
                        + pricingBox.getSelectedItem())
        );

        manageUsers.addActionListener(e ->
                addNotification("Admin accessed user management panel.")
        );

        securityButton.addActionListener(e ->
                addNotification("Security validation completed successfully.")
        );

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginPage();

        });

        panel.add(new JLabel("Select Pricing Strategy:"));
        panel.add(pricingBox);
        panel.add(applyPricing);
        panel.add(costLabel);
        panel.add(manageUsers);
        panel.add(securityButton);
        panel.add(logoutButton);

        return panel;
    }

    private void addEnergyUsage() {

        double newUsage = 2 + Math.random() * 8;
        newUsage = Math.round(newUsage * 10.0) / 10.0;

        energyData.add(newUsage);

        if (newUsage > 6) {

            addNotification("Warning: High energy usage detected: "
                    + newUsage + " kWh");

        } else {

            addNotification("Energy usage recorded: "
                    + newUsage + " kWh");
        }

        updateDashboard();

        if (graphPanel != null) {
            graphPanel.repaint();
        }
    }

    private void updateDashboard() {

        double total = 0;

        for (double usage : energyData) {
            total += usage;
        }

        double cost = total * 0.30;

        totalUsageLabel.setText(
                String.format("Total Usage: %.2f kWh", total)
        );

        costLabel.setText(
                String.format("Estimated Cost: £%.2f", cost)
        );
    }

    public void showMessage(String message) {

        addNotification(message);

        JOptionPane.showMessageDialog(this, message);
    }

    private void addNotification(String message) {

        notificationModel.addElement(message);
    }
}