package view;

import controller.EnergyController;
import model.Appliance;
import pattern.FlatRatePricing;
import pattern.GreenEnergyPricing;
import pattern.PeakHourPricing;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardView extends JFrame {

    private final EnergyController controller;

    private JTextField applianceNameField;
    private JTextField powerRatingField;
    private JTextField hoursField;
    private JTextField renewableEnergyField;

    private JComboBox<String> applianceTypeBox;
    private JComboBox<String> pricingBox;
    private JComboBox<String> renewableSourceBox;

    private JLabel totalUsageLabel;
    private JLabel estimatedCostLabel;

    private JTextArea outputArea;
    private JTextArea notificationArea;

    private EnergyGraphPanel graphPanel;

    private String runtimeMessage = "";

    public DashboardView(EnergyController controller) {
        this.controller = controller;

        setTitle("Smart Home Energy Management System");
        setSize(1250, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createHeaderPanel();
        createInputPanel();
        createCenterPanel();
        createAdminPanel();

        addNotification("Dashboard loaded successfully.");
        updateDashboard();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createHeaderPanel() {
        JLabel headerLabel = new JLabel("SHEMS Real-Time Energy Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(35, 70, 120));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(headerLabel, BorderLayout.NORTH);
    }

    private void createInputPanel() {

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder("User Appliance Input"));
        inputPanel.setBackground(new Color(245, 247, 250));
        inputPanel.setPreferredSize(new Dimension(260, 0));

        applianceNameField = new JTextField();
        powerRatingField = new JTextField();
        hoursField = new JTextField("4");
        renewableEnergyField = new JTextField("0");

        String[] applianceTypes = {
                "light", "ac", "fridge", "fan", "tv",
                "washing_machine", "heater", "microwave",
                "computer", "dishwasher"
        };

        String[] renewableSources = {
                "None", "Solar Panel", "Wind Energy", "Battery Storage"
        };

        applianceTypeBox = new JComboBox<>(applianceTypes);
        renewableSourceBox = new JComboBox<>(renewableSources);

        JButton addApplianceButton = new JButton("Add Appliance");
        JButton deleteApplianceButton = new JButton("Delete Appliance");
        JButton turnOnButton = new JButton("Turn All ON");
        JButton turnOffButton = new JButton("Turn All OFF");
        JButton malfunctionButton = new JButton("Report AC Malfunction");
        JButton viewReportButton = new JButton("View Report");
        JButton logoutButton = new JButton("Logout");

        setComponentSize(applianceNameField, 230, 30);
        setComponentSize(powerRatingField, 230, 30);
        setComponentSize(hoursField, 230, 30);
        setComponentSize(renewableEnergyField, 230, 30);
        setComponentSize(applianceTypeBox, 230, 30);
        setComponentSize(renewableSourceBox, 230, 30);

        setComponentSize(addApplianceButton, 230, 36);
        setComponentSize(deleteApplianceButton, 230, 36);
        setComponentSize(turnOnButton, 230, 36);
        setComponentSize(turnOffButton, 230, 36);
        setComponentSize(malfunctionButton, 230, 36);
        setComponentSize(viewReportButton, 230, 36);
        setComponentSize(logoutButton, 230, 36);

        styleButton(addApplianceButton, new Color(200, 230, 201));
        styleButton(deleteApplianceButton, new Color(255, 235, 238));
        styleButton(turnOnButton, new Color(187, 222, 251));
        styleButton(turnOffButton, new Color(255, 249, 196));
        styleButton(malfunctionButton, new Color(255, 224, 178));
        styleButton(viewReportButton, new Color(225, 245, 254));
        styleButton(logoutButton, new Color(255, 205, 210));

        inputPanel.add(Box.createVerticalStrut(8));

        addLabel(inputPanel, "Appliance Name:");
        inputPanel.add(applianceNameField);

        inputPanel.add(Box.createVerticalStrut(8));
        addLabel(inputPanel, "Appliance Type:");
        inputPanel.add(applianceTypeBox);

        inputPanel.add(Box.createVerticalStrut(8));
        addLabel(inputPanel, "Power Rating (kW):");
        inputPanel.add(powerRatingField);

        inputPanel.add(Box.createVerticalStrut(8));
        addLabel(inputPanel, "Hours Used:");
        inputPanel.add(hoursField);

        inputPanel.add(Box.createVerticalStrut(8));
        addLabel(inputPanel, "Renewable Source:");
        inputPanel.add(renewableSourceBox);

        inputPanel.add(Box.createVerticalStrut(8));
        addLabel(inputPanel, "Renewable Energy (kWh):");
        inputPanel.add(renewableEnergyField);

        inputPanel.add(Box.createVerticalStrut(12));

        JLabel actionLabel = new JLabel("Action Buttons:");
        actionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        actionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputPanel.add(actionLabel);

        inputPanel.add(Box.createVerticalStrut(8));
        inputPanel.add(addApplianceButton);
        inputPanel.add(Box.createVerticalStrut(6));
        inputPanel.add(deleteApplianceButton);
        inputPanel.add(Box.createVerticalStrut(6));
        inputPanel.add(turnOnButton);
        inputPanel.add(Box.createVerticalStrut(6));
        inputPanel.add(turnOffButton);
        inputPanel.add(Box.createVerticalStrut(6));
        inputPanel.add(malfunctionButton);
        inputPanel.add(Box.createVerticalStrut(6));
        inputPanel.add(viewReportButton);
        inputPanel.add(Box.createVerticalStrut(6));
        inputPanel.add(logoutButton);

        add(inputPanel, BorderLayout.WEST);

        addApplianceButton.addActionListener(e -> addUserAppliance());
        deleteApplianceButton.addActionListener(e -> deleteAppliance());
        turnOnButton.addActionListener(e -> turnAllAppliancesOn());
        turnOffButton.addActionListener(e -> turnAllAppliancesOff());
        viewReportButton.addActionListener(e -> viewReport());

        malfunctionButton.addActionListener(e -> {
            controller.reportMalfunction("Bedroom Air Conditioner");
            showRuntimeMessage("Malfunction alert reported for Bedroom Air Conditioner.");
            addNotification("Fault detected: Bedroom Air Conditioner.");
            updateDashboard();
        });

        logoutButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new LogoutPage();
            }
        });
    }

    private void createCenterPanel() {

        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));
        centerPanel.setBackground(new Color(240, 244, 248));

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 8, 8));
        topPanel.setBackground(new Color(240, 244, 248));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setBackground(new Color(252, 252, 252));

        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setBorder(BorderFactory.createTitledBorder("Runtime Output"));

        notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        notificationArea.setFont(new Font("Arial", Font.PLAIN, 13));
        notificationArea.setBackground(new Color(255, 248, 225));

        JScrollPane notificationScrollPane = new JScrollPane(notificationArea);
        notificationScrollPane.setBorder(BorderFactory.createTitledBorder("System Notifications"));

        topPanel.add(outputScrollPane);
        topPanel.add(notificationScrollPane);

        graphPanel = new EnergyGraphPanel();
        graphPanel.setBackground(Color.WHITE);
        graphPanel.setBorder(BorderFactory.createTitledBorder("Energy Usage Graph"));
        graphPanel.setPreferredSize(new Dimension(750, 330));

        centerPanel.add(topPanel, BorderLayout.CENTER);
        centerPanel.add(graphPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void createAdminPanel() {

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.setBorder(BorderFactory.createTitledBorder("Admin Pricing Panel"));
        adminPanel.setBackground(new Color(245, 247, 250));
        adminPanel.setPreferredSize(new Dimension(0, 120));

        totalUsageLabel = new JLabel("Total Usage: 0.00 kWh");
        estimatedCostLabel = new JLabel("Estimated Cost: £0.00");

        totalUsageLabel.setFont(new Font("Arial", Font.BOLD, 15));
        estimatedCostLabel.setFont(new Font("Arial", Font.BOLD, 15));

        String[] pricingOptions = {
                "Flat Rate Pricing",
                "Peak Hour Pricing",
                "Green Energy Pricing"
        };

        pricingBox = new JComboBox<>(pricingOptions);
        pricingBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        adminPanel.add(totalUsageLabel);
        adminPanel.add(Box.createVerticalStrut(8));
        adminPanel.add(new JLabel("Select Pricing Strategy:"));
        adminPanel.add(Box.createVerticalStrut(5));
        adminPanel.add(pricingBox);
        adminPanel.add(Box.createVerticalStrut(8));
        adminPanel.add(estimatedCostLabel);

        add(adminPanel, BorderLayout.SOUTH);

        pricingBox.addActionListener(e -> {
            updatePricingStrategy();
            showRuntimeMessage("Pricing strategy changed to " + pricingBox.getSelectedItem() + ".");
            addNotification("Pricing strategy updated: " + pricingBox.getSelectedItem() + ".");
            updateDashboard();
        });
    }

    private void addLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
    }

    private void setComponentSize(JComponent component, int width, int height) {
        Dimension size = new Dimension(width, height);
        component.setPreferredSize(size);
        component.setMaximumSize(size);
        component.setMinimumSize(size);
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 1));
    }

    private double getHours() {
        try {
            double hours = Double.parseDouble(hoursField.getText().trim());
            return hours < 0 ? 4 : hours;
        } catch (Exception e) {
            return 4;
        }
    }

    private double getRenewableEnergy() {
        try {
            double renewable = Double.parseDouble(renewableEnergyField.getText().trim());
            return Math.max(renewable, 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private double calculateNetUsage(double totalUsage, double renewableEnergy) {
        return Math.max(totalUsage - renewableEnergy, 0);
    }

    private void addUserAppliance() {

        try {
            String name = applianceNameField.getText().trim();
            String type = (String) applianceTypeBox.getSelectedItem();
            double powerRating = Double.parseDouble(powerRatingField.getText().trim());

            if (name.isEmpty()) {
                showRuntimeMessage("Error: Please enter appliance name.");
                addNotification("Input error: Appliance name is missing.");
                updateDashboard();
                return;
            }

            if (powerRating <= 0) {
                showRuntimeMessage("Error: Power rating must be greater than 0.");
                addNotification("Input error: Invalid power rating.");
                updateDashboard();
                return;
            }

            int id = controller.getSystem().getAppliances().size() + 1;
            controller.addAppliance(type, id, name, powerRating);

            applianceNameField.setText("");
            powerRatingField.setText("");

            showRuntimeMessage("Appliance added successfully: " + name + ".");
            addNotification("New appliance added: " + name + ".");

            updateDashboard();

        } catch (NumberFormatException e) {
            showRuntimeMessage("Error: Please enter a valid power rating.");
            addNotification("Input error: Power rating must be numeric.");
            updateDashboard();
        } catch (Exception e) {
            showRuntimeMessage("Error: " + e.getMessage());
            addNotification("System error: " + e.getMessage());
            updateDashboard();
        }
    }

    private void deleteAppliance() {

        String name = applianceNameField.getText().trim();

        if (name.isEmpty()) {
            showRuntimeMessage("Error: Enter appliance name to delete.");
            addNotification("Delete failed: appliance name was not provided.");
            updateDashboard();
            return;
        }

        List<Appliance> appliances = controller.getSystem().getAppliances();

        boolean removed = appliances.removeIf(
                appliance -> appliance.getName().equalsIgnoreCase(name)
        );

        if (removed) {
            showRuntimeMessage("Appliance deleted successfully: " + name + ".");
            addNotification("Appliance removed: " + name + ".");
            applianceNameField.setText("");
        } else {
            showRuntimeMessage("Error: Appliance not found: " + name + ".");
            addNotification("Delete failed: appliance not found.");
        }

        updateDashboard();
    }

    private void turnAllAppliancesOn() {

        List<Appliance> appliances = controller.getSystem().getAppliances();

        if (appliances.isEmpty()) {
            showRuntimeMessage("No appliances available to turn ON.");
            addNotification("Operation failed: no appliances available.");
            updateDashboard();
            return;
        }

        for (Appliance appliance : appliances) {
            controller.turnOnAppliance(appliance.getName());
        }

        showRuntimeMessage("All appliances turned ON successfully.");
        addNotification("All registered appliances activated.");

        updateDashboard();
    }

    private void turnAllAppliancesOff() {

        List<Appliance> appliances = controller.getSystem().getAppliances();

        if (appliances.isEmpty()) {
            showRuntimeMessage("No appliances available to turn OFF.");
            addNotification("Operation failed: no appliances available.");
            updateDashboard();
            return;
        }

        for (Appliance appliance : appliances) {
            controller.turnOffAppliance(appliance.getName());
        }

        showRuntimeMessage("All appliances turned OFF successfully.");
        addNotification("All registered appliances turned OFF.");

        updateDashboard();
    }

    private void viewReport() {

        double hours = getHours();
        double totalUsage = controller.calculateTotalUsage(hours);
        double renewableEnergy = getRenewableEnergy();
        double netUsage = calculateNetUsage(totalUsage, renewableEnergy);
        double estimatedCost = controller.calculateCost(netUsage);

        String report =
                "SHEMS SYSTEM REPORT\n\n" +
                        "Pricing Strategy: " + pricingBox.getSelectedItem() + "\n" +
                        "Renewable Source: " + renewableSourceBox.getSelectedItem() + "\n" +
                        "Hours Used: " + String.format("%.2f", hours) + "\n" +
                        "Total Usage Before Offset: " + String.format("%.2f", totalUsage) + " kWh\n" +
                        "Renewable Energy Generated: " + String.format("%.2f", renewableEnergy) + " kWh\n" +
                        "Net Usage After Renewable Offset: " + String.format("%.2f", netUsage) + " kWh\n" +
                        "Estimated Cost: £" + String.format("%.2f", estimatedCost) + "\n" +
                        "Total Appliances: " + controller.getSystem().getAppliances().size() + "\n" +
                        "System Status: " + (netUsage > 10 ? "High Energy Usage" : "Normal");

        JOptionPane.showMessageDialog(
                this,
                report,
                "SHEMS Report",
                JOptionPane.INFORMATION_MESSAGE
        );

        showRuntimeMessage("System report viewed by user.");
        addNotification("Report generated successfully.");
        updateDashboard();
    }

    private void updatePricingStrategy() {

        String selected = (String) pricingBox.getSelectedItem();

        if ("Flat Rate Pricing".equals(selected)) {
            controller.setPricingStrategy(new FlatRatePricing());
        } else if ("Peak Hour Pricing".equals(selected)) {
            controller.setPricingStrategy(new PeakHourPricing());
        } else if ("Green Energy Pricing".equals(selected)) {
            controller.setPricingStrategy(new GreenEnergyPricing());
        }
    }

    private void updateDashboard() {

        updatePricingStrategy();

        double hours = getHours();
        double totalUsage = controller.calculateTotalUsage(hours);
        double renewableEnergy = getRenewableEnergy();
        double netUsage = calculateNetUsage(totalUsage, renewableEnergy);
        double estimatedCost = controller.calculateCost(netUsage);

        totalUsageLabel.setText("Net Usage: " + String.format("%.2f", netUsage) + " kWh");
        estimatedCostLabel.setText("Estimated Cost: £" + String.format("%.2f", estimatedCost));

        if (netUsage > 20) {
            addNotification("Critical warning: net energy usage is above 20 kWh.");
        } else if (netUsage > 10) {
            addNotification("Warning: high net energy usage detected.");
        } else if (renewableEnergy > 0) {
            addNotification("Renewable energy offset applied successfully.");
        }

        updateRuntimeOutput(hours, totalUsage, renewableEnergy, netUsage, estimatedCost);

        graphPanel.setTotalUsage(netUsage);
        graphPanel.repaint();
    }

    private void updateRuntimeOutput(
            double hours,
            double totalUsage,
            double renewableEnergy,
            double netUsage,
            double estimatedCost
    ) {

        StringBuilder output = new StringBuilder();

        output.append("====================================\n");
        output.append(" SMART HOME ENERGY MANAGEMENT SYSTEM\n");
        output.append("====================================\n\n");

        output.append("Selected Pricing Strategy: ")
                .append(pricingBox.getSelectedItem())
                .append("\n");

        output.append("Renewable Source: ")
                .append(renewableSourceBox.getSelectedItem())
                .append("\n");

        output.append("Hours Used: ")
                .append(String.format("%.2f", hours))
                .append("\n");

        output.append("Total Usage Before Offset: ")
                .append(String.format("%.2f", totalUsage))
                .append(" kWh\n");

        output.append("Renewable Energy Generated: ")
                .append(String.format("%.2f", renewableEnergy))
                .append(" kWh\n");

        output.append("Net Usage After Renewable Offset: ")
                .append(String.format("%.2f", netUsage))
                .append(" kWh\n");

        output.append("Estimated Cost: £")
                .append(String.format("%.2f", estimatedCost))
                .append("\n\n");

        output.append("--- Appliance List ---\n");

        List<Appliance> appliances = controller.getSystem().getAppliances();

        if (appliances.isEmpty()) {
            output.append("No appliances added yet.\n");
        } else {
            for (Appliance appliance : appliances) {
                output.append("Name: ")
                        .append(appliance.getName())
                        .append(" | Type: ")
                        .append(appliance.getApplianceType())
                        .append(" | Power: ")
                        .append(appliance.getPowerRating())
                        .append(" kW")
                        .append(" | Health: ")
                        .append(appliance.isMalfunctioning() ? "FAULT" : "NORMAL")
                        .append("\n");
            }
        }

        output.append("\n--- System Notification ---\n");

        if (netUsage > 10) {
            output.append("High net energy usage detected.\n");
        } else {
            output.append("Energy usage is normal after renewable offset.\n");
        }

        if (!runtimeMessage.isEmpty()) {
            output.append("\n--- Latest Action ---\n");
            output.append(runtimeMessage).append("\n");
        }

        outputArea.setText(output.toString());
    }

    private void showRuntimeMessage(String message) {
        runtimeMessage = message;
    }

    private void addNotification(String message) {
        if (notificationArea != null) {
            notificationArea.append("• " + message + "\n");
        }
    }

    private static class EnergyGraphPanel extends JPanel {

        private double totalUsage = 10;

        private final double[] baseData = {
                2.5, 3.8, 4.2, 5.5, 6.1, 9.6, 9.6, 4.4, 8.9, 2.0
        };

        public void setTotalUsage(double totalUsage) {
            this.totalUsage = totalUsage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int leftMargin = 70;
            int bottomMargin = 45;
            int topMargin = 45;

            int baseY = panelHeight - bottomMargin;
            int chartHeight = panelHeight - topMargin - bottomMargin;

            int barWidth = 42;
            int gap = 25;
            int startX = 100;

            g2.setFont(new Font("Arial", Font.BOLD, 16));
            g2.setColor(Color.BLACK);
            g2.drawString("Energy Usage Graph", 40, 25);

            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.drawString("Usage", 20, baseY - chartHeight / 2);
            g2.drawString("Time Period", panelWidth / 2 - 40, panelHeight - 10);

            g2.drawLine(leftMargin, topMargin, leftMargin, baseY);
            g2.drawLine(leftMargin, baseY, panelWidth - 30, baseY);

            double maxValue = 10.0;

            for (int i = 0; i < baseData.length; i++) {

                double value = baseData[i];

                if (totalUsage > 0) {
                    value = baseData[i] * (totalUsage / 22.10);
                }

                if (value > 9.6) {
                    value = 9.6;
                }

                int barHeight = (int) ((value / maxValue) * chartHeight);

                int barX = startX + i * (barWidth + gap);
                int barY = baseY - barHeight;

                g2.setColor(Color.BLACK);
                g2.fillRect(barX, barY, barWidth, barHeight);

                g2.setFont(new Font("Arial", Font.BOLD, 12));
                g2.drawString(
                        String.format("%.1f kWh", value),
                        barX - 8,
                        barY - 8
                );

                g2.setFont(new Font("Arial", Font.BOLD, 13));
                g2.drawString(
                        "T" + (i + 1),
                        barX + 12,
                        baseY + 20
                );
            }
        }
    }
}