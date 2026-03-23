class Vehicle {
    private String vehicleId;
    private VehicleType type;

    public Vehicle(String vehicleId, VehicleType type) {
        this.vehicleId = vehicleId;
        this.type = type;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getType() {
        return type;
    }
}