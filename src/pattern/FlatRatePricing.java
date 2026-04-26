package pattern;

public class FlatRatePricing implements PricingStrategy {

    @Override
    public double calculateCost(double usage) {
        return usage * 0.30;
    }
}