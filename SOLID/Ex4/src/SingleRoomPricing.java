public class SingleRoomPricing implements RoomPricing {
    @Override
    public Money getMonthlyFee() {
        return new Money(14000.0);
    }

    @Override
    public int getRoomType() {
        return LegacyRoomTypes.SINGLE;
    }
}
