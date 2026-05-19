package model;

public class Dishwasher extends Appliance {

    public Dishwasher(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Dishwasher";
    }
}