package pattern;

import model.*;

// Factory class creates appliances dynamically
public class ApplianceFactory {

    public static Appliance createAppliance(String type, int id, String name, double powerRating) {
        switch (type.toLowerCase()) {
            case "light":
                return new Light(id, name, powerRating);

            case "ac":
                return new AirConditioner(id, name, powerRating);

            case "fridge":
                return new Fridge(id, name, powerRating);

            default:
                throw new IllegalArgumentException("Invalid appliance type: " + type);
        }
    }
}