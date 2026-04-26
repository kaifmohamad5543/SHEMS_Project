package model;

// Admin manages system users and pricing plans
public class Admin extends User {

    public Admin(int userId, String name, String email) {
        super(userId, name, email, "Admin");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Admin - manages users and pricing strategies.");
    }
}