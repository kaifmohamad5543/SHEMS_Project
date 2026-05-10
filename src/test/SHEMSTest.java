package test;

import model.*;
import pattern.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SHEMSTest {

    // =========================================================
    // Test 1 - Factory Pattern Appliance Creation
    // =========================================================
    @Test
    public void testApplianceCreationUsingFactory() {

        Appliance light =
                ApplianceFactory.createAppliance(
                        "Light",
                        1,
                        "Living Room Light",
                        2.5
                );

        assertNotNull(light);

        assertEquals(
                "Living Room Light",
                light.getName()
        );

        assertEquals(
                2.5,
                light.getPowerRating(),
                0.01
        );
    }

    // =========================================================
    // Test 2 - Flat Rate Pricing Strategy
    // =========================================================
    @Test
    public void testFlatRatePricingCalculation() {

        PricingStrategy strategy =
                new FlatRatePricing();

        double cost =
                strategy.calculateCost(10);

        assertEquals(
                3.0,
                cost,
                0.01
        );
    }

    // =========================================================
    // Test 3 - Peak Hour Pricing Strategy
    // =========================================================
    @Test
    public void testPeakHourPricingCalculation() {

        PricingStrategy strategy =
                new PeakHourPricing();

        double cost =
                strategy.calculateCost(10);

        assertTrue(cost > 3.0);
    }

    // =========================================================
    // Test 4 - Green Energy Pricing Strategy
    // =========================================================
    @Test
    public void testGreenEnergyPricingCalculation() {

        PricingStrategy strategy =
                new GreenEnergyPricing();

        double cost =
                strategy.calculateCost(10);

        assertTrue(cost < 3.0);
    }

    // =========================================================
    // Test 5 - Invalid Appliance Exception Handling
    // =========================================================
    @Test
    public void testInvalidApplianceTypeThrowsException() {

        assertThrows(
                IllegalArgumentException.class,

                () -> ApplianceFactory.createAppliance(
                        "Invalid",
                        99,
                        "Invalid Device",
                        3.0
                )
        );
    }

    // =========================================================
    // Test 6 - Performance Testing
    // =========================================================
    @Test
    public void testPerformanceWithMultipleAppliances() {

        long startTime =
                System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++) {

            Appliance appliance =
                    ApplianceFactory.createAppliance(
                            "Light",
                            i,
                            "Light " + i,
                            1.5
                    );

            assertNotNull(appliance);
        }

        long endTime =
                System.currentTimeMillis();

        long executionTime =
                endTime - startTime;

        assertTrue(executionTime < 2000);
    }
}