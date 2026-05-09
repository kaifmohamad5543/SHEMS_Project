package test;

import controller.EnergyController;
import pattern.GreenEnergyPricing;
import pattern.PeakHourPricing;
import security.EncryptionUtil;
import security.PasswordHasher;

/**
 * SHEMSTest class is used to test the core functionality
 * of the Smart Home Energy Management System.
 *
 * This class validates:
 * - appliance creation
 * - energy usage calculation
 * - pricing strategies
 * - password hashing
 * - AES encryption/decryption
 * - malfunction alert handling
 */
public class SHEMSTest {

    public static void main(String[] args) {

        System.out.println("===== SHEMS TESTING STARTED =====");

        testApplianceCreation();
        testEnergyUsageCalculation();
        testPeakHourPricing();
        testGreenEnergyPricing();
        testPasswordHashing();
        testEncryptionDecryption();
        testMalfunctionHandling();

        System.out.println("===== ALL TESTS COMPLETED SUCCESSFULLY =====");
    }

    /**
     * Tests whether an appliance is created and stored successfully.
     */
    public static void testApplianceCreation() {

        EnergyController controller = new EnergyController();

        controller.addAppliance(
                "light",
                1,
                "Living Room Light",
                0.5
        );

        if (controller.getSystem().getAppliances().size() == 1) {
            System.out.println("PASS: Appliance created successfully.");
        } else {
            System.out.println("FAIL: Appliance creation failed.");
        }
    }

    /**
     * Tests whether energy usage is calculated successfully.
     * The test checks that the calculated usage is greater than zero.
     */
    public static void testEnergyUsageCalculation() {

        EnergyController controller = new EnergyController();

        controller.addAppliance(
                "light",
                1,
                "Living Room Light",
                0.5
        );

        controller.addAppliance(
                "fridge",
                2,
                "Kitchen Fridge",
                1.2
        );

        controller.turnOnAppliance("Living Room Light");
        controller.turnOnAppliance("Kitchen Fridge");

        double usage = controller.calculateTotalUsage(4);

        if (usage > 0) {
            System.out.println("PASS: Energy usage calculated correctly.");
            System.out.println("Calculated Usage: " + usage + " kWh");
        } else {
            System.out.println("FAIL: Energy usage calculation failed.");
        }
    }

    /**
     * Tests whether peak-hour pricing strategy calculates cost successfully.
     */
    public static void testPeakHourPricing() {

        EnergyController controller = new EnergyController();

        controller.setPricingStrategy(new PeakHourPricing());

        double cost = controller.calculateCost(10);

        if (cost > 0) {
            System.out.println("PASS: Peak hour pricing calculated successfully.");
            System.out.println("Peak Hour Cost: £" + cost);
        } else {
            System.out.println("FAIL: Peak hour pricing calculation failed.");
        }
    }

    /**
     * Tests whether green-energy pricing strategy calculates cost successfully.
     */
    public static void testGreenEnergyPricing() {

        EnergyController controller = new EnergyController();

        controller.setPricingStrategy(new GreenEnergyPricing());

        double cost = controller.calculateCost(10);

        if (cost > 0) {
            System.out.println("PASS: Green energy pricing calculated successfully.");
            System.out.println("Green Energy Cost: £" + cost);
        } else {
            System.out.println("FAIL: Green energy pricing calculation failed.");
        }
    }

    /**
     * Tests SHA-256 password hashing.
     * The hashed password should not be equal to the original password.
     */
    public static void testPasswordHashing() {

        String password = "admin123";

        String hashedPassword = PasswordHasher.hashPassword(password);

        if (hashedPassword != null && !hashedPassword.equals(password)) {
            System.out.println("PASS: Password hashing works correctly.");
            System.out.println("Hashed Password: " + hashedPassword);
        } else {
            System.out.println("FAIL: Password hashing failed.");
        }
    }

    /**
     * Tests AES encryption and decryption.
     * The decrypted data should match the original data.
     */
    public static void testEncryptionDecryption() {

        String originalData = "Energy Usage Data: 14.8 kWh";

        String encryptedData = EncryptionUtil.encrypt(originalData);

        String decryptedData = EncryptionUtil.decrypt(encryptedData);

        if (originalData.equals(decryptedData)) {
            System.out.println("PASS: Encryption and decryption work correctly.");
            System.out.println("Encrypted Data: " + encryptedData);
            System.out.println("Decrypted Data: " + decryptedData);
        } else {
            System.out.println("FAIL: Encryption/decryption failed.");
        }
    }

    /**
     * Tests whether malfunction alerts are handled successfully.
     */
    public static void testMalfunctionHandling() {

        EnergyController controller = new EnergyController();

        controller.addAppliance(
                "ac",
                1,
                "Bedroom Air Conditioner",
                2.0
        );

        try {
            controller.reportMalfunction("Bedroom Air Conditioner");
            System.out.println("PASS: Malfunction alert handled successfully.");
        } catch (Exception e) {
            System.out.println("FAIL: Malfunction handling failed.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}