package model;

public class Television extends Appliance {

    public Television(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Television";
    }
}