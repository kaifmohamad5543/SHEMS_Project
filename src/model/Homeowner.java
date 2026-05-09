package model;

// Homeowner class inherits from the User parent class
// Represents the primary user who manages household energy usage
public class Homeowner extends User {

    // Constructor used to initialise homeowner details
    public Homeowner(int userId, String name, String email) {

        // Calls the parent User constructor
        // Automatically assigns the role as "Homeowner"
        super(userId, name, email, "Homeowner");
    }

    // Method overriding used to display homeowner-specific role information
    @Override
    public void displayRole() {

        // Displays the responsibilities of the homeowner user
        System.out.println("Role: Homeowner - manages home energy usage.");
    }
}