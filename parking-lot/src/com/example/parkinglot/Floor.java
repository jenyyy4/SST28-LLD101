import java.util.ArrayList;
import java.util.List;

class Floor {
    private int floorId;
    private List<ParkingSlot> slots;

    public Floor(int floorId) {
        this.floorId = floorId;
        this.slots = new ArrayList<>();
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public int getFloorId() {
        return floorId;
    }
}