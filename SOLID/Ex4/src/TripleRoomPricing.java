public class TripleRoomPricing implements RoomPricing {
    @Override
    public Money getMonthlyFee() {
        return new Money(12000.0);
    }

    @Override
    public int getRoomType() {
        return LegacyRoomTypes.TRIPLE;
    }
}
