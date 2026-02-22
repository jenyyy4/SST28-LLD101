import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricingFactory roomPricingFactory;
    private final AddOnPricingFactory addOnPricingFactory;

    public HostelFeeCalculator(FakeBookingRepo repo, RoomPricingFactory roomPricingFactory, 
                               AddOnPricingFactory addOnPricingFactory) {
        this.repo = repo;
        this.roomPricingFactory = roomPricingFactory;
        this.addOnPricingFactory = addOnPricingFactory;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        List<PricingComponent> components = new ArrayList<>();
        
        RoomPricing roomPricing = roomPricingFactory.getPricing(req.roomType);
        components.add(roomPricing);

        for (AddOn addOn : req.addOns) {
            AddOnPricing addOnPricing = addOnPricingFactory.getPricing(addOn);
            if (addOnPricing != null) {
                components.add(addOnPricing);
            }
        }

        Money total = new Money(0.0);
        for (PricingComponent component : components) {
            total = total.plus(component.getMonthlyFee());
        }

        return total;
    }
}
