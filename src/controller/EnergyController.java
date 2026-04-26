package controller;

import model.Appliance;
import model.EnergyUsage;
import pattern.*;

import java.util.ArrayList;
import java.util.List;

public class EnergyController {
    private EnergyManagementSystem system;
    private PricingStrategy pricingStrategy;
    private NotificationService notificationService;

    public EnergyController() {
        system = EnergyManagementSystem.getInstance();
        pricingStrategy = new FlatRatePricing();

        notificationService = new NotificationService();
        notificationService.addObserver(new HomeownerObserver());
        notificationService.addObserver(new TechnicianObserver());
    }

    public void addAppliance(String type, int id, String name, double powerRating) {
        Appliance appliance = ApplianceFactory.createAppliance(type, id, name, powerRating);
        system.addAppliance(appliance);
    }

    public void turnOnAppliance(String name) {
        for (Appliance appliance : system.getAppliances()) {
            if (appliance.getName().equalsIgnoreCase(name)) {
                appliance.turnOn();
                System.out.println(name + " turned ON successfully.");
            }
        }
    }

    public void turnOffAppliance(String name) {
        for (Appliance appliance : system.getAppliances()) {
            if (appliance.getName().equalsIgnoreCase(name)) {
                appliance.turnOff();
                System.out.println(name + " turned OFF successfully.");
            }
        }
    }

    public void reportMalfunction(String name) {
        for (Appliance appliance : system.getAppliances()) {
            if (appliance.getName().equalsIgnoreCase(name)) {
                appliance.reportMalfunction();
                notificationService.notifyObservers(
                        "Malfunction detected in " + appliance.getName()
                );
            }
        }
    }

    public double calculateTotalUsage(double hours) {
        double totalUsage = 0;

        for (Appliance appliance : system.getAppliances()) {
            EnergyUsage usage = new EnergyUsage(appliance, hours);
            totalUsage += usage.getTotalUsage();
        }

        if (totalUsage > 10) {
            notificationService.notifyObservers(
                    "High energy usage detected: " + totalUsage + " kWh"
            );
        }

        return totalUsage;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateCost(double usage) {
        return pricingStrategy.calculateCost(usage);
    }

    public EnergyManagementSystem getSystem() {
        return system;
    }
}