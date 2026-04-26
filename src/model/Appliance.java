package model;

public abstract class Appliance {
    private int applianceId;
    private String name;
    private double powerRating;
    private boolean status;
    private boolean malfunctioning;

    public Appliance(int applianceId, String name, double powerRating) {
        this.applianceId = applianceId;
        this.name = name;
        this.powerRating = powerRating;
        this.status = false;
        this.malfunctioning = false;
    }

    public void turnOn() {
        status = true;
    }

    public void turnOff() {
        status = false;
    }

    public double calculateUsage(double hours) {
        if (!status) {
            return 0;
        }
        return powerRating * hours;
    }

    public void reportMalfunction() {
        malfunctioning = true;
    }

    public boolean isMalfunctioning() {
        return malfunctioning;
    }

    public String getName() {
        return name;
    }

    public double getPowerRating() {
        return powerRating;
    }

    public boolean isOn() {
        return status;
    }

    public abstract String getApplianceType();
}