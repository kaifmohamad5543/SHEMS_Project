package pattern;

public class HomeownerObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Homeowner Alert: " + message);
    }
}