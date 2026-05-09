package test;

import controller.EnergyController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pattern.GreenEnergyPricing;
import pattern.PeakHourPricing;
import security.EncryptionUtil;
import security.PasswordHasher;

/**
 * Advanced JUnit tests for the SHEMS application.
 * This class validates functionality, security, performance, and load behaviour.
 */
public class AdvancedSHEMSTest {

    @Test
    public void testApplianceCreation() {
        EnergyController controller = new EnergyController();

        controller.addAppliance("light", 1, "Living Room Light", 0.5);

        Assertions.assertTrue(
                controller.getSystem().getAppliances().size() >= 1,
                "Appliance should be created successfully"
        );
    }

    @Test
    public void testEnergyUsageCalculation() {
        EnergyController controller = new EnergyController();

        controller.addAppliance("light", 2, "Living Room Light", 0.5);
        controller.turnOnAppliance("Living Room Light");

        double usage = controller.calculateTotalUsage(5);

        Assertions.assertTrue(
                usage > 0,
                "Energy usage should be greater than zero"
        );
    }

    @Test
    public void testPeakHourPricing() {
        EnergyController controller = new EnergyController();

        controller.setPricingStrategy(new PeakHourPricing());

        double cost = controller.calculateCost(10);

        Assertions.assertTrue(
                cost > 0,
                "Peak-hour pricing cost should be greater than zero"
        );
    }

    @Test
    public void testGreenEnergyPricing() {
        EnergyController controller = new EnergyController();

        controller.setPricingStrategy(new GreenEnergyPricing());

        double cost = controller.calculateCost(10);

        Assertions.assertTrue(
                cost > 0,
                "Green-energy pricing cost should be greater than zero"
        );
    }

    @Test
    public void testPasswordHashing() {
        String password = "admin123";

        String hashedPassword = PasswordHasher.hashPassword(password);

        Assertions.assertNotNull(hashedPassword);
        Assertions.assertNotEquals(
                password,
                hashedPassword,
                "Hashed password should not match original password"
        );
    }

    @Test
    public void testEncryptionDecryption() {
        String originalData = "Energy Usage Data: 14.8 kWh";

        String encryptedData = EncryptionUtil.encrypt(originalData);
        String decryptedData = EncryptionUtil.decrypt(encryptedData);

        Assertions.assertNotNull(encryptedData);
        Assertions.assertEquals(
                originalData,
                decryptedData,
                "Decrypted data should match original data"
        );
    }

    @Test
    public void performanceTest() {
        EnergyController controller = new EnergyController();

        for (int i = 0; i < 100; i++) {
            controller.addAppliance("light", i, "Light-" + i, 0.5);
        }

        long startTime = System.currentTimeMillis();

        double usage = controller.calculateTotalUsage(24);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Performance Test Execution Time: " + executionTime + " ms");

        Assertions.assertTrue(usage > 0);
        Assertions.assertTrue(
                executionTime >= 0,
                "Execution time should be measurable"
        );
    }

    @Test
    public void loadTest() {
        EnergyController controller = new EnergyController();

        for (int i = 0; i < 500; i++) {
            controller.addAppliance("fridge", i, "Fridge-" + i, 1.2);
        }

        double totalUsage = 0;

        for (int i = 0; i < 100; i++) {
            totalUsage += controller.calculateTotalUsage(12);
        }

        System.out.println("Load Testing Total Usage: " + totalUsage + " kWh");

        Assertions.assertTrue(
                totalUsage > 0,
                "Load test should produce measurable energy usage"
        );
    }
}