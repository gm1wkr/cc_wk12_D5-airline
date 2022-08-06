public class Passenger {

    private String name;
    private int numberOfBags;
    private String flight;
    private int seat;

    public Passenger(String name, int numberOfBags) {
        this.name = name;
        this.numberOfBags = numberOfBags;
        this.flight = null;
        this.seat = -1;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfBags() {
        return this.numberOfBags;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public int getSeat() {
        return this.seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
