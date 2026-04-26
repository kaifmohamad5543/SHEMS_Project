package pattern;

import model.Appliance;
import java.util.ArrayList;
import java.util.List;

// Singleton class for central energy system control
public class EnergyManagementSystem {
    private static EnergyManagementSystem instance;
    private List<Appliance> appliances;

    private EnergyManagementSystem() {
        appliances = new ArrayList<>();
    }

    public static EnergyManagementSystem getInstance() {
        if (instance == null) {
            instance = new EnergyManagementSystem();
        }
        return instance;
    }

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }
}