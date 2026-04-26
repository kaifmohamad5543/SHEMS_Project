import controller.EnergyController;
import model.Appliance;
import model.DeviceSchedule;
import pattern.GreenEnergyPricing;
import pattern.PeakHourPricing;
import view.DashboardView;

public class Main {

    public static void main(String[] args) {
        EnergyController controller = new EnergyController();
        DashboardView view = new DashboardView();

        controller.addAppliance("light", 1, "Living Room Light", 0.5);
        controller.addAppliance("ac", 2, "Bedroom Air Conditioner", 2.0);
        controller.addAppliance("fridge", 3, "Kitchen Fridge", 1.2);

        controller.turnOnAppliance("Living Room Light");
        controller.turnOnAppliance("Bedroom Air Conditioner");
        controller.turnOnAppliance("Kitchen Fridge");

        double usage = controller.calculateTotalUsage(4);
        double cost = controller.calculateCost(usage);

        view.showDashboard(controller.getSystem().getAppliances(), usage, cost);

        System.out.println("\n--- Pricing Strategy Comparison ---");

        controller.setPricingStrategy(new PeakHourPricing());
        System.out.println("Peak Hour Cost: £" + controller.calculateCost(usage));

        controller.setPricingStrategy(new GreenEnergyPricing());
        System.out.println("Green Energy Cost: £" + controller.calculateCost(usage));

        System.out.println("\n--- Device Schedule Example ---");
        Appliance firstAppliance = controller.getSystem().getAppliances().get(0);
        DeviceSchedule schedule = new DeviceSchedule(firstAppliance, "18:00", "22:00");
        schedule.showSchedule();

        System.out.println("\n--- Malfunction Alert Example ---");
        controller.reportMalfunction("Bedroom Air Conditioner");

        view.showDashboard(controller.getSystem().getAppliances(), usage, cost);
    }
}