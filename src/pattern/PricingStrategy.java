package pattern;

// Strategy interface for different pricing plans
public interface PricingStrategy {
    double calculateCost(double usage);
}