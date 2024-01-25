import java.util.*;

// Enum for different types of seats
enum SeatType {
    NORMAL,
    VIP
}

// Class representing a Seat in the theater
class Seat {
    private int seatNumber;
    private SeatType type;
    private boolean isBooked;

    public Seat(int seatNumber, SeatType type) {
        this.seatNumber = seatNumber;
        this.type = type;
        this.isBooked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getType() {
        return type;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        isBooked = true;
    }

    public void cancelBooking() {
        isBooked = false;
    }
}

// Class representing a Movie
class Movie {
    private String title;
    private String description;
    private Date releaseDate;
    private List<Showtime> showtimes;

    public Movie(String title, String description, Date releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.showtimes = new ArrayList<>();
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    // Other getters and setters
}

// Class representing a Showtime for a movie
class Showtime {
    private Movie movie;
    private Date startTime;
    private int durationMinutes;
    private List<Seat> seats;

    public Showtime(Movie movie, Date startTime, int durationMinutes, List<Seat> seats) {
        this.movie = movie;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.seats = seats;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    // Other getters and setters
}

// Class representing a Theater
class Theater {
    private String name;
    private List<Showtime> showtimes;

    public Theater(String name) {
        this.name = name;
        this.showtimes = new ArrayList<>();
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    // Other getters and setters
}

// Class representing a Ticket
class Ticket {
    private Showtime showtime;
    private Seat seat;
    private double price;

    public Ticket(Showtime showtime, Seat seat, double price) {
        this.showtime = showtime;
        this.seat = seat;
        this.price = price;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
}

// Class representing a Booking
class Booking {
    private List<Ticket> tickets;
    private double totalPrice;

    public Booking() {
        this.tickets = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        totalPrice += ticket.getPrice();
        ticket.getSeat().book(); // Mark seat as booked
    }

    public void cancelTicket(Ticket ticket) {
        tickets.remove(ticket);
        totalPrice -= ticket.getPrice();
        ticket.getSeat().cancelBooking(); // Mark seat as available
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

// Main class to demonstrate usage
public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        // Sample usage of the classes
        // Create movies
        Movie movie1 = new Movie("The Matrix", "Action", new Date());
        Movie movie2 = new Movie("Inception", "Sci-Fi", new Date());

        // Create seats for showtimes
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Seat seat = new Seat(i, SeatType.NORMAL);
            seats.add(seat);
        }

        // Create showtimes for movies
        Showtime showtime1 = new Showtime(movie1, new Date(), 120, seats);
        Showtime showtime2 = new Showtime(movie2, new Date(), 150, seats);

        // Create theaters and add showtimes
        Theater theater1 = new Theater("Cinema 1");
        theater1.addShowtime(showtime1);
        theater1.addShowtime(showtime2);

        // Sample booking
        Booking booking = new Booking();
        Ticket ticket1 = new Ticket(showtime1, seats.get(0), 10.0);
        booking.addTicket(ticket1);
        Ticket ticket2 = new Ticket(showtime2, seats.get(1), 12.0);
        booking.addTicket(ticket2);

        // Output booking details
        System.out.println("Booking Details:");
        for (Ticket ticket : booking.getTickets()) {
            System.out.println("Movie: " + ticket.getShowtime().getMovie().getTitle() +
                    ", Seat: " + ticket.getSeat().getSeatNumber() +
                    ", Price: $" + ticket.getPrice());
        }
        System.out.println("Total Price: $" + booking.getTotalPrice());

        // Cancel one ticket
        booking.cancelTicket(ticket1);
        System.out.println("\nAfter cancellation:");
        System.out.println("Remaining Tickets:");
        for (Ticket ticket : booking.getTickets()) {
            System.out.println("Movie: " + ticket.getShowtime().getMovie().getTitle() +
                    ", Seat: " + ticket.getSeat().getSeatNumber() +
                    ", Price: $" + ticket.getPrice());
        }
        System.out.println("Total Price: $" + booking.getTotalPrice());
    }
}
