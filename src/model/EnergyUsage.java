package model;

public class EnergyUsage {
    private Appliance appliance;
    private double hoursUsed;

    public EnergyUsage(Appliance appliance, double hoursUsed) {
        this.appliance = appliance;
        this.hoursUsed = hoursUsed;
    }

    public double getTotalUsage() {
        return appliance.calculateUsage(hoursUsed);
    }

    public String getUsageReport() {
        return appliance.getName() + " used " + getTotalUsage() + " kWh for " + hoursUsed + " hours.";
    }
}