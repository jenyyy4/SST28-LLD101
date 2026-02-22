public class LaundryPricing implements AddOnPricing {
    @Override
    public Money getMonthlyFee() {
        return new Money(500.0);
    }

    @Override
    public AddOn getAddOn() {
        return AddOn.LAUNDRY;
    }
}
