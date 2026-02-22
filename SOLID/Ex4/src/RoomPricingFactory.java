import java.util.*;

public class RoomPricingFactory {
    private final Map<Integer, RoomPricing> pricingMap = new HashMap<>();

    public RoomPricingFactory() {
        register(new SingleRoomPricing());
        register(new DoubleRoomPricing());
        register(new TripleRoomPricing());
        register(new DeluxeRoomPricing());
    }

    private void register(RoomPricing pricing) {
        pricingMap.put(pricing.getRoomType(), pricing);
    }

    public RoomPricing getPricing(int roomType) {
        return pricingMap.getOrDefault(roomType, new DeluxeRoomPricing());
    }
}
