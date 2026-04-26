package model;

public class AirConditioner extends Appliance {

    public AirConditioner(int applianceId, String name, double powerRating) {
        super(applianceId, name, powerRating);
    }

    @Override
    public String getApplianceType() {
        return "Air Conditioner";
    }
}