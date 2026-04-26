package pattern;

public class PeakHourPricing implements PricingStrategy {

    @Override
    public double calculateCost(double usage) {
        return usage * 0.45;
    }
}