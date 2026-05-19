package model;

public class Heater extends Appliance {

    public Heater(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Heater";
    }
}