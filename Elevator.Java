import java.util.*;

enum Direction {
    UP, DOWN, IDLE
}

class Request {
    private int floor;
    private Direction direction;

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}

class Elevator {
    private int currentFloor;
    private Direction direction;
    private Set<Request> requests;

    public Elevator() {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.requests = new HashSet<>();
    }

    public void addRequest(int floor, Direction direction) {
        requests.add(new Request(floor, direction));
    }

    public void move() {
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        Request nextRequest = getNextRequest();
        if (nextRequest.getFloor() > currentFloor) {
            direction = Direction.UP;
            currentFloor++;
        } else if (nextRequest.getFloor() < currentFloor) {
            direction = Direction.DOWN;
            currentFloor--;
        } else {
            // Handle arrival at requested floor
            System.out.println("Elevator reached floor " + currentFloor);
            requests.remove(nextRequest);
        }
    }

    private Request getNextRequest() {
        // Simple FCFS (First-Come-First-Served) scheduling
        return requests.iterator().next();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}

class ElevatorSystem {
    private List<Elevator> elevators;

    public ElevatorSystem(int numElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator());
        }
    }

    public boolean requestElevator(int floor, Direction direction) {
        // Assign the request to the elevator with the minimum load
        Elevator selectedElevator = elevators.get(0);
        for (Elevator elevator : elevators) {
            if (elevator.getCurrentFloor() == floor && elevator.direction == Direction.IDLE) {
                selectedElevator = elevator;
                break;
            }
            if (elevator.direction == Direction.IDLE) {
                selectedElevator = elevator;
                break;
            }
        }

        // If no IDLE elevator found, return false
        if (selectedElevator == null || selectedElevator.direction != Direction.IDLE) {
            return false;
        }

        selectedElevator.addRequest(floor, direction);
        return true;
    }

    public void step() {
        // Move each elevator to its next requested floor
        for (Elevator elevator : elevators) {
            elevator.move();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Sample usage
        ElevatorSystem elevatorSystem = new ElevatorSystem(3);

        // User requests
        elevatorSystem.requestElevator(5, Direction.UP);
        elevatorSystem.requestElevator(2, Direction.DOWN);
        elevatorSystem.requestElevator(8, Direction.UP);

        // Simulation steps
        for (int i = 0; i < 10; i++) {
            elevatorSystem.step();
        }
    }
}
