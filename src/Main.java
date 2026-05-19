import controller.EnergyController;
import model.Admin;
import model.Homeowner;
import model.Technician;
import pattern.FlatRatePricing;
import pattern.GreenEnergyPricing;
import pattern.PeakHourPricing;
import security.EncryptionUtil;
import security.PasswordHasher;
import view.LoginPage;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" SMART HOME ENERGY MANAGEMENT SYSTEM ");
        System.out.println("======================================");

        Admin admin = new Admin(1, "System Admin", "admin@example.com");
        Homeowner homeowner = new Homeowner(2, "Home Owner", "homeowner@example.com");
        Technician technician = new Technician(3, "Technician", "technician@example.com");

        admin.displayRole();
        homeowner.displayRole();
        technician.displayRole();

        EnergyController controller = new EnergyController();

        controller.addAppliance("light", 1, "Living Room Light", 0.5);
        controller.addAppliance("ac", 2, "Bedroom Air Conditioner", 2.0);
        controller.addAppliance("fridge", 3, "Kitchen Fridge", 1.2);

        controller.turnOnAppliance("Living Room Light");
        controller.turnOnAppliance("Bedroom Air Conditioner");
        controller.turnOnAppliance("Kitchen Fridge");

        controller.setPricingStrategy(new FlatRatePricing());

        double usage = controller.calculateTotalUsage(4);
        double cost = controller.calculateCost(usage);

        System.out.println("\n--- Runtime Energy Summary ---");
        System.out.println("Total Usage: " + usage + " kWh");
        System.out.println("Estimated Cost: £" + String.format("%.2f", cost));

        System.out.println("\n--- Pricing Strategy Comparison ---");

        controller.setPricingStrategy(new FlatRatePricing());
        System.out.println("Flat Rate Cost: £" + String.format("%.2f", controller.calculateCost(usage)));

        controller.setPricingStrategy(new PeakHourPricing());
        System.out.println("Peak Hour Cost: £" + String.format("%.2f", controller.calculateCost(usage)));

        controller.setPricingStrategy(new GreenEnergyPricing());
        System.out.println("Green Energy Cost: £" + String.format("%.2f", controller.calculateCost(usage)));

        controller.setPricingStrategy(new FlatRatePricing());

        System.out.println("\n--- Device Scheduling Example ---");
        System.out.println("Schedule: Living Room Light will run from 18:00 to 22:00");

        System.out.println("\n--- Malfunction Alert Example ---");
        controller.reportMalfunction("Bedroom Air Conditioner");

        System.out.println("\n--- Password Hashing Example ---");
        String password = "admin123";
        String hashedPassword = PasswordHasher.hashPassword(password);

        System.out.println("Original Password: " + password);
        System.out.println("SHA-256 Hash: " + hashedPassword);

        System.out.println("\n--- AES Encryption Example ---");

        try {
            String originalData = "Energy Usage Data: " + usage + " kWh";
            String encryptedData = EncryptionUtil.encrypt(originalData);
            String decryptedData = EncryptionUtil.decrypt(encryptedData);

            System.out.println("Original Data: " + originalData);
            System.out.println("Encrypted Data: " + encryptedData);
            System.out.println("Decrypted Data: " + decryptedData);

        } catch (Exception e) {
            System.out.println("Encryption Error: " + e.getMessage());
        }

        System.out.println("\n--- EMAIL NOTIFICATION ---");
        System.out.println("To: homeowner@example.com");
        System.out.println("Subject: High Energy Usage Alert");
        System.out.println("Message: High energy usage detected.");
        System.out.println("Status: Email sent successfully (simulated)");

        System.out.println("\n--- SMS NOTIFICATION ---");
        System.out.println("To: +447000000000");
        System.out.println("Message: SHEMS Alert - High energy usage detected.");
        System.out.println("Status: SMS sent successfully (simulated)");

        SwingUtilities.invokeLater(LoginPage::new);
    }
}