package model;

// Technician class inherits from the User parent class
// Represents a technician responsible for appliance maintenance
public class Technician extends User {

    // Constructor used to initialise technician details
    public Technician(int userId, String name, String email) {

        // Calls the parent User constructor
        // Automatically assigns the role as "Technician"
        super(userId, name, email, "Technician");
    }

    // Method overriding used to display technician-specific role information
    @Override
    public void displayRole() {

        // Displays the responsibility of the technician user
        System.out.println("Role: Technician - handles appliance maintenance.");
    }
}