import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStorage storage;
    private final TaxCalculator taxCalculator;
    private final DiscountCalculator discountCalculator;
    private final PricingCalculator pricingCalculator;
    private final InvoiceBuilder invoiceBuilder;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStorage storage, TaxCalculator taxCalculator, 
                          DiscountCalculator discountCalculator) {
        this.storage = storage;
        this.taxCalculator = taxCalculator;
        this.discountCalculator = discountCalculator;
        this.pricingCalculator = new PricingCalculator(menu);
        this.invoiceBuilder = new InvoiceBuilder(pricingCalculator);
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = pricingCalculator.calculateSubtotal(lines);
        double taxPct = taxCalculator.getTaxPercent(customerType);
        double tax = taxCalculator.calculateTax(customerType, subtotal);
        double discount = discountCalculator.calculateDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String invoice = invoiceBuilder.buildInvoice(invId, lines, subtotal, taxPct, tax, discount, total);
        System.out.print(invoice);

        storage.save(invId, invoice);
        System.out.println("Saved invoice: " + invId + " (lines=" + storage.countLines(invId) + ")");
    }
}
