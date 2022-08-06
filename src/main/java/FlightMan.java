import java.time.LocalDateTime;

public class FlightMan extends Flight{


    private final int weightPerBag;
    private final int baggageAllowance;

    public FlightMan(Aircraft aircraft, Pilot pilot, String flightNumber, String departureCode, String destinationCode, LocalDateTime departureTime) {
        super(aircraft, pilot, flightNumber, departureCode, destinationCode, departureTime);
        this.weightPerBag = 10;
        this.baggageAllowance = 20;
        this.aircraft = aircraft;
    }


    public int weightAvailableForBags() {
        return aircraft.getTotalWeight() / 2;
    }

    public int getBaggageAllowance() {
        return baggageAllowance;
    }

    public boolean withinBaggageLimit(Passenger pax) {
        return pax.getNumberOfBags() * this.weightPerBag <= this.baggageAllowance;
    }

    public int getTotalBaggageWeight() {
        return this.getNumberOfPassengers() * this.getBaggageAllowance();
    }

    public int getTotalBaggageWeightRemaining() {
        return this.weightAvailableForBags() - this.getTotalBaggageWeight();
    }

    public int getPaxBagWeight(Passenger passenger) {
        return passenger.getNumberOfBags() * this.weightPerBag;
    }
}
