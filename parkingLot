import java.util.HashMap;
import java.util.Map;

enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

class Vehicle {
    private String licensePlate;
    private VehicleSize size;

    public Vehicle(String licensePlate, VehicleSize size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public VehicleSize getSize() {
        return size;
    }
}

class ParkingSpot {
    private int spotNumber;
    private VehicleSize size;
    private boolean occupied;

    public ParkingSpot(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupySpot() {
        occupied = true;
    }

    public void vacateSpot() {
        occupied = false;
    }
}

class ParkingLot {
    private Map<Integer, ParkingSpot> spots;

    public ParkingLot() {
        spots = new HashMap<>();
    }

    public void addSpot(int spotNumber, VehicleSize size) {
        spots.put(spotNumber, new ParkingSpot(spotNumber, size));
    }

    public int parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots.values()) {
            if (!spot.isOccupied() && spot.getSize() == vehicle.getSize()) {
                spot.occupySpot();
                return spotNumber;
            }
        }
        return -1; // No available spot for the vehicle
    }

    public void vacateSpot(int spotNumber) {
        ParkingSpot spot = spots.get(spotNumber);
        if (spot != null) {
            spot.vacateSpot();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addSpot(1, VehicleSize.SMALL);
        parkingLot.addSpot(2, VehicleSize.MEDIUM);
        parkingLot.addSpot(3, VehicleSize.LARGE);

        Vehicle car = new Vehicle("ABC123", VehicleSize.SMALL);
        int spotNumber = parkingLot.parkVehicle(car);

        if (spotNumber != -1) {
            System.out.println("Car parked at spot number: " + spotNumber);
        } else {
            System.out.println("No available spot for the car.");
        }

        parkingLot.vacateSpot(spotNumber);
    }
}
