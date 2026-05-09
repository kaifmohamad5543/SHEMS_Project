package controller;

import model.Appliance;
import model.EnergyUsage;
import pattern.*;

/**
 * EnergyController acts as the Controller layer in the MVC architecture.
 * It manages appliance operations, energy calculation, pricing strategies,
 * and notification alerts within the SHEMS application.
 */
public class EnergyController {

    // Singleton instance of the Energy Management System
    private final EnergyManagementSystem system;

    // Strategy Pattern reference for dynamic pricing calculation
    private PricingStrategy pricingStrategy;

    // Observer Pattern service for homeowner and technician alerts
    private final NotificationService notificationService;

    /**
     * Constructor initialises the central system, default pricing strategy,
     * and notification observers.
     */
    public EnergyController() {

        // Singleton Pattern: get one shared system instance
        system = EnergyManagementSystem.getInstance();

        // Strategy Pattern: default pricing strategy is flat-rate pricing
        pricingStrategy = new FlatRatePricing();

        // Observer Pattern: create notification service
        notificationService = new NotificationService();

        // Observer Pattern: homeowner receives high-usage and system alerts
        notificationService.addObserver(new HomeownerObserver());

        // Observer Pattern: technician receives malfunction/maintenance alerts
        notificationService.addObserver(new TechnicianObserver());
    }

    /**
     * Adds an appliance dynamically using the Factory Pattern.
     */
    public void addAppliance(String type, int id, String name, double powerRating) {

        // Factory Pattern: creates appliance object based on appliance type
        Appliance appliance = ApplianceFactory.createAppliance(type, id, name, powerRating);

        // Add appliance to the central energy management system
        system.addAppliance(appliance);

        // Confirmation message for runtime output
        System.out.println(name + " added successfully.");
    }

    /**
     * Turns ON a selected appliance by name.
     */
    public void turnOnAppliance(String name) {

        Appliance appliance = findApplianceByName(name);

        if (appliance != null) {
            appliance.turnOn();
            System.out.println(name + " turned ON successfully.");
        } else {
            System.out.println("Appliance not found: " + name);
        }
    }

    /**
     * Turns OFF a selected appliance by name.
     */
    public void turnOffAppliance(String name) {

        Appliance appliance = findApplianceByName(name);

        if (appliance != null) {
            appliance.turnOff();
            System.out.println(name + " turned OFF successfully.");
        } else {
            System.out.println("Appliance not found: " + name);
        }
    }

    /**
     * Reports appliance malfunction and notifies observers.
     */
    public void reportMalfunction(String name) {

        Appliance appliance = findApplianceByName(name);

        if (appliance != null) {

            // Mark appliance as malfunctioning
            appliance.reportMalfunction();

            // Notify homeowner and technician observers
            notificationService.notifyObservers(
                    "Malfunction detected in " + appliance.getName()
            );

        } else {
            System.out.println("Cannot report malfunction. Appliance not found: " + name);
        }
    }

    /**
     * Calculates total energy usage for all appliances.
     */
    public double calculateTotalUsage(double hours) {

        double totalUsage = 0;

        // Calculate usage for each appliance
        for (Appliance appliance : system.getAppliances()) {

            EnergyUsage usage = new EnergyUsage(appliance, hours);

            totalUsage += usage.getTotalUsage();
        }

        // Notify observers when energy usage is high
        if (totalUsage > 10) {
            notificationService.notifyObservers(
                    "High energy usage detected: " + totalUsage + " kWh"
            );
        }

        return totalUsage;
    }

    /**
     * Changes pricing strategy dynamically using Strategy Pattern.
     */
    public void setPricingStrategy(PricingStrategy pricingStrategy) {

        if (pricingStrategy != null) {
            this.pricingStrategy = pricingStrategy;
            System.out.println("Pricing strategy updated successfully.");
        } else {
            System.out.println("Invalid pricing strategy.");
        }
    }

    /**
     * Calculates electricity cost using the selected pricing strategy.
     */
    public double calculateCost(double usage) {

        return pricingStrategy.calculateCost(usage);
    }

    /**
     * Finds appliance by name to reduce repeated loop logic.
     */
    private Appliance findApplianceByName(String name) {

        for (Appliance appliance : system.getAppliances()) {

            if (appliance.getName().equalsIgnoreCase(name)) {
                return appliance;
            }
        }

        return null;
    }

    /**
     * Returns the central energy management system instance.
     */
    public EnergyManagementSystem getSystem() {

        return system;
    }
}