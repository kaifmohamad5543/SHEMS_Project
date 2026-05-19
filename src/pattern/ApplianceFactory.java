package pattern;

import model.*;

public class ApplianceFactory {

    public static Appliance createAppliance(
            String type,
            int id,
            String name,
            double powerRating
    ) {

        switch (type.toLowerCase()) {

            case "light":
                return new Light(id, name, powerRating);

            case "ac":
                return new AirConditioner(id, name, powerRating);

            case "fridge":
                return new Fridge(id, name, powerRating);

            case "fan":
                return new Fan(id, name, powerRating);

            case "tv":
                return new Television(id, name, powerRating);

            case "washing_machine":
                return new WashingMachine(id, name, powerRating);

            case "heater":
                return new Heater(id, name, powerRating);

            case "microwave":
                return new Microwave(id, name, powerRating);

            case "computer":
                return new Computer(id, name, powerRating);

            case "dishwasher":
                return new Dishwasher(id, name, powerRating);

            default:
                throw new IllegalArgumentException(
                        "Invalid appliance type."
                );
        }
    }
}