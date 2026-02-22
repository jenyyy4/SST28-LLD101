import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        
        RoomPricingFactory roomPricingFactory = new RoomPricingFactory();
        AddOnPricingFactory addOnPricingFactory = new AddOnPricingFactory();
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), roomPricingFactory, addOnPricingFactory);
        
        calc.process(req);
    }
}
