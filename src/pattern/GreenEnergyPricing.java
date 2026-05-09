package pattern;

// GreenEnergyPricing class implements the PricingStrategy interface
// Represents environmentally friendly energy pricing with reduced cost
public class GreenEnergyPricing implements PricingStrategy {

    // Method overriding used to calculate green-energy pricing cost
    @Override
    public double calculateCost(double usage) {

        // Calculates cost using discounted green-energy pricing rate
        return usage * 0.20;
    }
}