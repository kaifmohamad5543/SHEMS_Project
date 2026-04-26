package model;

// Technician receives device malfunction alerts
public class Technician extends User {

    public Technician(int userId, String name, String email) {
        super(userId, name, email, "Technician");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Technician - handles appliance maintenance.");
    }
}