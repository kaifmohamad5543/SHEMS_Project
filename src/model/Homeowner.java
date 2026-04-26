package model;

// Homeowner can manage appliances and monitor energy usage
public class Homeowner extends User {

    public Homeowner(int userId, String name, String email) {
        super(userId, name, email, "Homeowner");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Homeowner - manages home energy usage.");
    }
}