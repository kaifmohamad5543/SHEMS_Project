package model;

// Admin class inherits from the User parent class
// Represents the administrator responsible for managing the system
public class Admin extends User {

    // Constructor used to initialise admin details
    public Admin(int userId, String name, String email) {

        // Calls the parent User constructor
        // Automatically assigns the role as "Admin"
        super(userId, name, email, "Admin");
    }

    // Method overriding used to display admin-specific role information
    @Override
    public void displayRole() {

        // Displays the responsibilities of the admin user
        System.out.println("Role: Admin - manages users and pricing strategies.");
    }
}