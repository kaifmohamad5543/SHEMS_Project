package model;

public class Fridge extends Appliance {

    public Fridge(int applianceId, String name, double powerRating) {
        super(applianceId, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Fridge";
    }
}