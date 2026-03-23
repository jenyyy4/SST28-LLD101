import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ParkingLotService service = new ParkingLotService();
        Scanner sc = new Scanner(System.in);

        // Setup
        Floor f1 = new Floor(1);

        ParkingSlot s1 = new ParkingSlot(1, SlotType.SMALL);
        ParkingSlot s2 = new ParkingSlot(2, SlotType.MEDIUM);
        ParkingSlot s3 = new ParkingSlot(3, SlotType.LARGE);

        s1.setDistance(1, 10);
        s2.setDistance(1, 5);
        s3.setDistance(1, 15);

        f1.addSlot(s1);
        f1.addSlot(s2);
        f1.addSlot(s3);

        service.addFloor(f1);

        while (true) {

            System.out.println("\n===== CURRENT AVAILABILITY =====");
            service.status();

            System.out.println("\n==== Parking Lot Menu ====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Exit Vehicle");
            System.out.println("3. Check Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Available Slots Before Parking ---");
                    service.status();

                    System.out.print("Enter Vehicle ID: ");
                    String id = sc.next();

                    System.out.print("Enter Vehicle Type (BIKE/CAR/BUS): ");
                    VehicleType vType = VehicleType.valueOf(sc.next().toUpperCase());

                    System.out.print("Enter Requested Slot Type (SMALL/MEDIUM/LARGE): ");
                    SlotType sType = SlotType.valueOf(sc.next().toUpperCase());

                    System.out.print("Enter Gate ID: ");
                    int gateId = sc.nextInt();

                    Vehicle vehicle = new Vehicle(id, vType);

                    long entryTime = System.currentTimeMillis();

                    Ticket ticket = service.park(vehicle, entryTime, sType, gateId);

                    if (ticket != null) {
                        System.out.println("Vehicle Parked!");
                        System.out.println("Ticket ID: " + ticket.getTicketId());
                        System.out.println("Allocated Slot: " + ticket.getSlot().getSlotId());
                        System.out.println("Slot Type: " + ticket.getSlotType());
                    }
                    break;

                case 2:
                    System.out.print("Enter Ticket ID: ");
                    String ticketId = sc.next();

                    long exitTime = System.currentTimeMillis();

                    try {
                        double amount = service.exit(ticketId, exitTime);
                        System.out.println("Total Bill: ₹" + amount);
                    } catch (Exception e) {
                        System.out.println("Invalid Ticket!");
                    }
                    break;

                case 3:
                    service.status();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}