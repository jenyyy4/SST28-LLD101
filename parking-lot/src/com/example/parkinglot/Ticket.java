class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private SlotType slotType;
    private long entryTime;
    private int entryGateId;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSlot slot,
                  long entryTime, int entryGateId) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.slotType = slot.getType(); // explicitly stored
        this.entryTime = entryTime;
        this.entryGateId = entryGateId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public SlotType getSlotType() {
        return slotType;
    }
}