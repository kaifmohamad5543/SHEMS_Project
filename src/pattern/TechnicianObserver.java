package pattern;

public class TechnicianObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Technician Alert: " + message);
    }
}