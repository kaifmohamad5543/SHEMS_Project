package pattern;

// PeakHourPricing class implements the PricingStrategy interface
// Represents pricing calculation during peak electricity usage hours
public class PeakHourPricing implements PricingStrategy {

    // Method overriding used to calculate energy cost dynamically
    @Override
    public double calculateCost(double usage) {

        // Calculates cost using peak-hour pricing rate
        return usage * 0.45;
    }
}