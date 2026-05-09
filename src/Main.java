import controller.EnergyController;
import model.Appliance;
import model.DeviceSchedule;
import pattern.GreenEnergyPricing;
import pattern.PeakHourPricing;
import security.PasswordHasher;
import security.EncryptionUtil;
import view.DashboardView;
import notification.NotificationService;

public class Main {

    public static void main(String[] args) {

        // Create controller
        EnergyController controller = new EnergyController();

        // Create GUI dashboard
        DashboardView dashboard = new DashboardView();

        // Add appliances
        controller.addAppliance("light", 1, "Living Room Light", 0.5);
        controller.addAppliance("ac", 2, "Bedroom Air Conditioner", 2.0);
        controller.addAppliance("fridge", 3, "Kitchen Fridge", 1.2);

        // Turn on appliances
        controller.turnOnAppliance("Living Room Light");
        controller.turnOnAppliance("Bedroom Air Conditioner");
        controller.turnOnAppliance("Kitchen Fridge");

        // Calculate energy usage and cost
        double usage = controller.calculateTotalUsage(4);
        double cost = controller.calculateCost(usage);

        // Display dashboard
        dashboard.setVisible(true);

        // Console output
        System.out.println("\n--- SHEMS Dashboard Running ---");

        System.out.println("\n--- Pricing Strategy Comparison ---");

        controller.setPricingStrategy(new PeakHourPricing());
        System.out.println("Peak Hour Cost: £" + controller.calculateCost(usage));

        controller.setPricingStrategy(new GreenEnergyPricing());
        System.out.println("Green Energy Cost: £" + controller.calculateCost(usage));

        // Device scheduling example
        System.out.println("\n--- Device Schedule Example ---");

        Appliance firstAppliance =
                controller.getSystem().getAppliances().get(0);

        DeviceSchedule schedule =
                new DeviceSchedule(firstAppliance, "18:00", "22:00");

        schedule.showSchedule();

        // Observer pattern notification example
        System.out.println("\n--- Malfunction Alert Example ---");

        controller.reportMalfunction("Bedroom Air Conditioner");

        // Security implementation
        System.out.println("\n--- Password Hashing Example ---");

        String password = "admin123";

        String hashedPassword =
                PasswordHasher.hashPassword(password);

        System.out.println("Original Password: " + password);

        System.out.println("SHA-256 Hashed Password: "
                + hashedPassword);

        // AES Encryption Example
        System.out.println("\n--- Encryption and Decryption Example ---");

        String sensitiveData =
                "Energy Usage Data: 14.8 kWh";

        String encryptedData =
                EncryptionUtil.encrypt(sensitiveData);

        String decryptedData =
                EncryptionUtil.decrypt(encryptedData);

        System.out.println("Original Data: " + sensitiveData);

        System.out.println("Encrypted Data: " + encryptedData);

        System.out.println("Decrypted Data: " + decryptedData);


        System.out.println("\n--- Email/SMS Notification Example ---");

        NotificationService notificationService = new NotificationService();

        if (usage > 10) {
            notificationService.sendEmail(
                    "homeowner@example.com",
                    "High Energy Usage Alert",
                    "Your energy usage is high. Current usage: " + usage + " kWh"
            );

            notificationService.sendSMS(
                    "+447000000000",
                    "SHEMS Alert: High energy usage detected: " + usage + " kWh"
            );
        } else {
            notificationService.sendEmail(
                    "homeowner@example.com",
                    "Energy Usage Report",
                    "Your current energy usage is normal: " + usage + " kWh"
            );
        }
    }
}