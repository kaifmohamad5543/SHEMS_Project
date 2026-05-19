package model;

public class Microwave extends Appliance {

    public Microwave(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Microwave";
    }
}