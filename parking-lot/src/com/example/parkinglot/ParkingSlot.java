import java.util.HashMap;
import java.util.Map;

class ParkingSlot {
    private int slotId;
    private SlotType type;
    private boolean isOccupied;
    private Map<Integer, Integer> gateDistance;

    public ParkingSlot(int slotId, SlotType type) {
        this.slotId = slotId;
        this.type = type;
        this.isOccupied = false;
        this.gateDistance = new HashMap<>();
    }

    public void setDistance(int gateId, int distance) {
        gateDistance.put(gateId, distance);
    }

    public int getDistance(int gateId) {
        return gateDistance.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void free() {
        isOccupied = false;
    }

    public SlotType getType() {
        return type;
    }

    public int getSlotId() {
        return slotId;
    }
}