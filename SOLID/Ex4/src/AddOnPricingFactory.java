import java.util.*;

public class AddOnPricingFactory {
    private final Map<AddOn, AddOnPricing> pricingMap = new HashMap<>();

    public AddOnPricingFactory() {
        register(new MessPricing());
        register(new LaundryPricing());
        register(new GymPricing());
    }

    private void register(AddOnPricing pricing) {
        pricingMap.put(pricing.getAddOn(), pricing);
    }

    public AddOnPricing getPricing(AddOn addOn) {
        return pricingMap.get(addOn);
    }
}
