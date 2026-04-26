package model;

public class Light extends Appliance {

    public Light(int applianceId, String name, double powerRating) {
        super(applianceId, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Light";
    }
}