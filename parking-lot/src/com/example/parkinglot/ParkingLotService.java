import java.util.*;

class ParkingLotService {

    private List<Floor> floors;
    private Map<SlotType, Double> rates;
    private Map<String, Ticket> activeTickets;

    public ParkingLotService() {
        floors = new ArrayList<>();
        rates = new HashMap<>();
        activeTickets = new HashMap<>();

        rates.put(SlotType.SMALL, 10.0);
        rates.put(SlotType.MEDIUM, 20.0);
        rates.put(SlotType.LARGE, 50.0);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

        private List<SlotType> getAllowedSlots(VehicleType type) {
        switch (type) {
            case BIKE:
                return Arrays.asList(SlotType.SMALL, SlotType.MEDIUM, SlotType.LARGE);
            case CAR:
                return Arrays.asList(SlotType.MEDIUM, SlotType.LARGE);
            case BUS:
                return Arrays.asList(SlotType.LARGE);
        }
        return new ArrayList<>();
    }

        private ParkingSlot findSlot(VehicleType vType, SlotType requestedType, int gateId) {

        List<SlotType> allowed = getAllowedSlots(vType);

        // Step 1: Try requested slot type first
        List<SlotType> priorityList = new ArrayList<>();

        if (requestedType != null && allowed.contains(requestedType)) {
            priorityList.add(requestedType);
        }

        // Step 2: Add larger compatible slots
        for (SlotType type : allowed) {
            if (!priorityList.contains(type)) {
                priorityList.add(type);
            }
        }

        ParkingSlot best = null;
        int minDist = Integer.MAX_VALUE;

        for (SlotType type : priorityList) {
            for (Floor floor : floors) {
                for (ParkingSlot slot : floor.getSlots()) {

                    if (!slot.isOccupied() && slot.getType() == type) {
                        int dist = slot.getDistance(gateId);

                        if (dist < minDist) {
                            minDist = dist;
                            best = slot;
                        }
                    }
                }
            }
            if (best != null) return best; // stop once found
        }

        return null;
    }

        public Ticket park(Vehicle vehicle, long entryTime,
                       SlotType requestedType, int gateId) {

        ParkingSlot slot = findSlot(vehicle.getType(), requestedType, gateId);

        if (slot == null) {
            System.out.println("No slot available!");
            return null;
        }

        slot.occupy();

        String ticketId = UUID.randomUUID().toString();

        Ticket ticket = new Ticket(ticketId, vehicle, slot, entryTime, gateId);

        activeTickets.put(ticketId, ticket);

        return ticket;
    }

        public double exit(String ticketId, long exitTime) {

        Ticket ticket = activeTickets.get(ticketId);

        if (ticket == null) {
            throw new RuntimeException("Invalid Ticket!");
        }

        ParkingSlot slot = ticket.getSlot();

        double hours = (exitTime - ticket.getEntryTime()) / 3600000.0;
        hours = Math.ceil(hours);

        double rate = rates.get(ticket.getSlotType());
        double amount = hours * rate;

        slot.free();
        activeTickets.remove(ticketId);

        return amount;
    }

        public void status() {
        Map<SlotType, Integer> count = new HashMap<>();

        for (Floor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (!slot.isOccupied()) {
                    count.put(slot.getType(),
                            count.getOrDefault(slot.getType(), 0) + 1);
                }
            }
        }

        System.out.println("Available Slots:");
        System.out.println("SMALL: " + count.getOrDefault(SlotType.SMALL, 0));
        System.out.println("MEDIUM: " + count.getOrDefault(SlotType.MEDIUM, 0));
        System.out.println("LARGE: " + count.getOrDefault(SlotType.LARGE, 0));
    }
}