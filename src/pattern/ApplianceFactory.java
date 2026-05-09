package pattern;
import model.*;
// Factory class responsible for creating appliance objects dynamically
public class ApplianceFactory {
    // Static factory method used to generate appliance objects
    public static Appliance createAppliance(String type, int id, String name, double powerRating) {
        // Selects appliance type based on user input
        switch (type.toLowerCase()) {
            // Creates and returns a Light appliance object
            case "light":
                return new Light(id, name, powerRating);
                // Creates and returns an Air Conditioner appliance object
            case "ac":
                return new AirConditioner(id, name, powerRating);
                // Creates and returns a Fridge appliance object
            case "fridge":
                return new Fridge(id, name, powerRating);
                // Handles invalid appliance types entered by the user
            default:
                // Throws an exception if the appliance type is unsupported
                throw new IllegalArgumentException("Invalid appliance type: " + type);
        }
    }
}