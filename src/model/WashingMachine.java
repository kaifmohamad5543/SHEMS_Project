package model;

public class WashingMachine extends Appliance {

    public WashingMachine(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Washing Machine";
    }
}
