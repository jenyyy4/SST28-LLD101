public interface TaxCalculator {
    double calculateTax(String customerType, double subtotal);
    double getTaxPercent(String customerType);
}
