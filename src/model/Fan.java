package model;

public class Fan extends Appliance {

    public Fan(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Fan";
    }
}