import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Flight {

    Aircraft aircraft;
    private Pilot pilot;
    private ArrayList<CabinCrewMember> cabinCrewMembers;
    private ArrayList<Passenger> passengers;
    private String flightNumber;
    private String destinationCode;
    private String departureCode;
    private LocalDateTime departureTime;

    public Flight(Aircraft aircraft, Pilot pilot, String flightNumber, String departureCode, String destinationCode, LocalDateTime departureTime) {
        this.aircraft = aircraft;
        this.flightNumber = flightNumber;
        this.departureCode = departureCode;
        this.destinationCode = destinationCode;
        this.departureTime = departureTime;
        this.pilot = pilot;
        this.cabinCrewMembers = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public String getDepartureCode() {
        return this.departureCode;
    }

    public String getDestinationCode() {
        return this.destinationCode;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public int getNumberOfCabinCrew() {
        return this.cabinCrewMembers.size();
    }

    public void addCabinCrew(CabinCrewMember cabinCrew) {
        cabinCrewMembers.add(cabinCrew);
    }

    public boolean hasFullCabinCrew() {
        return cabinCrewMembers.size() >= 3;
    }

    public boolean hasPurser() {
        for(CabinCrewMember purser : cabinCrewMembers){
            if(purser.getRank() == "Purser"){
                return true;
            }
        }
        return false;
    }


    public int getNumberOfPassengers() {
        return this.passengers.size();
    }


    public boolean hasAvailableSeats(Aircraft aircraft) {
        return aircraft.getCapacity() > this.getNumberOfPassengers();
    }

    public int getRemainingSeats() {
        return aircraft.getCapacity() - this.getNumberOfPassengers();
    }

    public int generateSeatNumber() {
        return ThreadLocalRandom.current().nextInt(1, aircraft.getCapacity() + 1);
    }

    public boolean isSeatFree(int seatNumber){
        for (Passenger passenger : passengers){
            if(passenger.getSeat() == seatNumber){
                return false;
            }
        }
        return true;
    }

    public boolean checkInPassenger(Passenger passengerToAdd) {
        if(passengers.contains(passengerToAdd)){
            return false;
        }
        this.passengers.add(passengerToAdd);
        passengerToAdd.setFlight(this.getFlightNumber());

        int seatNumber = this.generateSeatNumber();
        if(this.isSeatFree(seatNumber)){
            passengerToAdd.setSeat(seatNumber);
            return true;
        }
        return false;
    }

    public boolean isPaxOnFlight(Passenger passenger) {
        return passengers.contains(passenger);
    }


}
