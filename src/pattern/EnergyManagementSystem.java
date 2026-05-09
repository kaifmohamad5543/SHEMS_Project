package pattern;
import model.Appliance;
import java.util.ArrayList;
import java.util.List;
// Singleton class responsible for managing the central energy system
public class EnergyManagementSystem {
    // Static object used to store the single instance of the class
    private static EnergyManagementSystem instance;
    // List used to store all registered appliances
    private List<Appliance> appliances;
    // Private constructor prevents direct object creation from outside the class
    private EnergyManagementSystem() {
        // Initialises the appliance list
        appliances = new ArrayList<>();}
    // Public method used to access the single shared instance
    public static EnergyManagementSystem getInstance() {
        // Creates the object only if it does not already exist
        if (instance == null) {
            // Creates a new singleton instance
            instance = new EnergyManagementSystem();}
        // Returns the single shared object
        return instance;}
    // Adds a new appliance to the energy management system
    public void addAppliance(Appliance appliance) {
        // Stores the appliance in the appliance list
        appliances.add(appliance);}
    // Returns the list of all appliances in the system
    public List<Appliance> getAppliances() {
        // Provides access to the appliance collection
        return appliances;
    }
}