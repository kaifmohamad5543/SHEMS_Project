package model;
// Abstract parent class representing a general appliance
public abstract class Appliance {
    // Unique ID used to identify each appliance
    private int applianceId;
    // Name of the appliance (e.g., Light, AC, Fridge)
    private String name;
    // Power consumption rating of the appliance
    private double powerRating;
    // Stores whether the appliance is currently ON or OFF
    private boolean status;
    // Tracks whether the appliance is malfunctioning
    private boolean malfunctioning;
    // Constructor used to initialise appliance details
    public Appliance(int applianceId, String name, double powerRating) {
        this.applianceId = applianceId;
        this.name = name;
        this.powerRating = powerRating;
        // Appliances are OFF by default when created
        this.status = false;
        // Appliance starts in a normal working condition
        this.malfunctioning = false;}
    // Method used to turn the appliance ON
    public void turnOn() {
        status = true;}
    // Method used to turn the appliance OFF
    public void turnOff() {
        status = false;}
    // Calculates energy usage based on power rating and usage hours
    public double calculateUsage(double hours) {
        // If appliance is OFF, no energy is consumed
        if (!status) {
            return 0;}
        // Energy usage calculation
        return powerRating * hours;}
    // Marks the appliance as malfunctioning
    public void reportMalfunction() {
        malfunctioning = true;}
    // Checks whether the appliance has malfunctioned
    public boolean isMalfunctioning() {
        return malfunctioning;}
    // Returns the appliance name
    public String getName() {
        return name;}
    // Returns the appliance power rating
    public double getPowerRating() {
        return powerRating;}
    // Checks whether the appliance is ON or OFF
    public boolean isOn() {
        return status;}
    // Abstract method implemented differently by each appliance type
    public abstract String getApplianceType();}