import java.util.*;

public class InvoiceBuilder {
    private final PricingCalculator pricingCalculator;

    public InvoiceBuilder(PricingCalculator pricingCalculator) {
        this.pricingCalculator = pricingCalculator;
    }

    public String buildInvoice(String invoiceId, List<OrderLine> lines, double subtotal, 
                               double taxPct, double tax, double discount, double total) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invoiceId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = pricingCalculator.getMenuItem(l.itemId);
            double lineTotal = pricingCalculator.calculateLineTotal(l);
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        return out.toString();
    }
}
