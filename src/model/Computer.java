package model;

public class Computer extends Appliance {

    public Computer(int id, String name, double powerRating) {
        super(id, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Computer";
    }
}