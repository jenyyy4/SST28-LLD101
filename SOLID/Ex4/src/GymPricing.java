public class GymPricing implements AddOnPricing {
    @Override
    public Money getMonthlyFee() {
        return new Money(300.0);
    }

    @Override
    public AddOn getAddOn() {
        return AddOn.GYM;
    }
}
