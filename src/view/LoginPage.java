package view;

import cloud.CloudDeploymentService;
import controller.EnergyController;
import pattern.FlatRatePricing;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {

        setTitle("Smart Home Energy Management System");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("SHEMS LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(120, 25, 250, 40);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setBounds(60, 90, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(170, 90, 180, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setBounds(60, 140, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 140, 180, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 210, 110, 40);

        loginButton.addActionListener(e -> login());

        add(title);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin Login Successful"
            );

            dispose();

            EnergyController controller = new EnergyController();

            controller.addAppliance("light", 1, "Living Room Light", 0.5);
            controller.addAppliance("ac", 2, "Bedroom Air Conditioner", 2.0);
            controller.addAppliance("fridge", 3, "Kitchen Fridge", 1.2);

            controller.turnOnAppliance("Living Room Light");
            controller.turnOnAppliance("Bedroom Air Conditioner");
            controller.turnOnAppliance("Kitchen Fridge");

            controller.setPricingStrategy(new FlatRatePricing());

            DashboardView dashboard = new DashboardView(controller);

            JOptionPane.showMessageDialog(
                    dashboard,
                    "Admin authenticated successfully. Dashboard access granted."
            );

            CloudDeploymentService cloudService =
                    new CloudDeploymentService(
                            "AWS EC2",
                            "SHEMS-Cloud-Server"
                    );

            cloudService.deployApplication();
            cloudService.checkServerStatus();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );

            usernameField.setText("");
            passwordField.setText("");
        }
    }
}