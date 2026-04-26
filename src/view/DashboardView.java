package view;

import model.Appliance;

import java.util.List;

public class DashboardView {

    public void showDashboard(List<Appliance> appliances, double usage, double cost) {
        System.out.println("\n======================================");
        System.out.println(" SMART HOME ENERGY MANAGEMENT SYSTEM ");
        System.out.println("======================================");

        System.out.println("Total Usage: " + usage + " kWh");
        System.out.println("Estimated Cost: £" + cost);

        System.out.println("\nAppliance Status:");
        for (Appliance appliance : appliances) {
            System.out.println(
                    "- " + appliance.getName()
                            + " | Type: " + appliance.getApplianceType()
                            + " | Power: " + appliance.getPowerRating() + " kW"
                            + " | Status: " + (appliance.isOn() ? "ON" : "OFF")
                            + " | Health: " + (appliance.isMalfunctioning() ? "FAULT" : "NORMAL")
            );
        }
    }
}