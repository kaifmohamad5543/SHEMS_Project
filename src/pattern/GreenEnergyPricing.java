package pattern;

public class GreenEnergyPricing implements PricingStrategy {

    @Override
    public double calculateCost(double usage) {
        return usage * 0.20;
    }
}