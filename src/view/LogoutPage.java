package view;

import javax.swing.*;
import java.awt.*;

public class LogoutPage extends JFrame {

    public LogoutPage() {

        setTitle("SHEMS Logout");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(
                "You have logged out successfully.",
                SwingConstants.CENTER
        );

        messageLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        JButton loginAgainButton =
                new JButton("Login Again");

        JButton exitButton =
                new JButton("Exit System");

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(loginAgainButton);
        buttonPanel.add(exitButton);

        add(messageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginAgainButton.addActionListener(e -> {

            dispose();

            new LoginPage();
        });

        exitButton.addActionListener(e -> {

            System.exit(0);
        });

        setVisible(true);
    }
}