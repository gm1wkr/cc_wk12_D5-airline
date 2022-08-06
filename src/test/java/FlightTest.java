import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.*;

public class FlightTest {

    Flight flight;
    Aircraft aircraft;
    Aircraft aircraftFull;
    Pilot captain;
    Pilot firstOfficer;
    CabinCrewMember cabinCrewMember1;
    CabinCrewMember cabinCrewMember2;
    CabinCrewMember cabinCrewMember3;
    Passenger passenger1;
    Passenger passenger2;
    Passenger passenger3;
    Passenger passengerOverWeight;
    LocalDateTime departureTime;

    @Before
    public void before(){
        String flightNumber = "Speedbird1";
        String departureCode = "IBA";
        String destinationCode = "ABZ";
        String departureTimeString = "06 Aug 2022 1100";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm", Locale.ENGLISH);
        departureTime = LocalDateTime.parse(departureTimeString, formatter);
        aircraft = new Aircraft(AircraftType.BOEING752.getCapacity(), AircraftType.BOEING752.getTotalWeight());
        aircraftFull = new Aircraft(AircraftType.BOEINGFULL.getCapacity(), AircraftType.BOEINGFULL.getTotalWeight());
        captain = new Pilot("Erik", "Captain", "abc123");
        cabinCrewMember1 = new CabinCrewMember("Jilly", "Flight Attendant");
        cabinCrewMember2 = new CabinCrewMember("Boaby", "Flight Attendant");
        cabinCrewMember3 = new CabinCrewMember("Henretta", "Purser");
        passenger1 = new Passenger("James", 2);
        passenger2 = new Passenger("Sumin", 3);
        passenger3 = new Passenger("Sally", 1);
        passengerOverWeight = new Passenger("Boris", 10);
        flight = new Flight(aircraft, captain, flightNumber, departureCode, destinationCode, departureTime);
    }

    @Test
    public void flightHasAircraft(){
        assertEquals(200, aircraft.getCapacity());
    }

    @Test
    public void flightHasCaptain(){
        assertEquals("Captain", captain.getRank());
    }

    @Test
    public void flightHasFlightNumber(){
        assertEquals("Speedbird1", flight.getFlightNumber());
    }

    @Test
    public void flightHasDepartureCode(){
        assertEquals("IBA", flight.getDepartureCode());
    }

    @Test
    public void flightHasDestinationCode(){
        assertEquals("ABZ", flight.getDestinationCode());
    }

    @Test
    public void flightHasDepartureTime(){
        assertEquals(departureTime, flight.getDepartureTime());
    }

    @Test
    public void flightStartsWithNoCrew(){
        assertEquals(0, flight.getNumberOfCabinCrew());
    }

    @Test
    public void canAddFlightCrew(){
        flight.addCabinCrew(cabinCrewMember3);
        assertEquals(1, flight.getNumberOfCabinCrew());
    }

    @Test
    public void flightHasEnoughCabinCrew(){
        flight.addCabinCrew(cabinCrewMember1);
        flight.addCabinCrew(cabinCrewMember2);
        flight.addCabinCrew(cabinCrewMember3);
        assertTrue(flight.hasFullCabinCrew());
    }

    @Test
    public void flightHasPurser(){
        flight.addCabinCrew(cabinCrewMember1);
        flight.addCabinCrew(cabinCrewMember2);
        flight.addCabinCrew(cabinCrewMember3);
        assertTrue(flight.hasPurser());
    }

    @Test
    public void flightStartsWithNoPassengers(){
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    public void hasNoRemainingSeats(){
        assertFalse(flight.hasAvailableSeats(aircraftFull));
    }

    @Test
    public void hasRemainingSeats(){
        assertTrue(flight.hasAvailableSeats(aircraft));
    }

    @Test
    public void getAvailableSeatsRemaining(){
        flight.checkInPassenger(passenger1);
        flight.checkInPassenger(passenger2);
        assertEquals(198, flight.getRemainingSeats());
    }

    @Test
    public void isPaxAlreadyBookedOnFlightTrue(){
        flight.checkInPassenger(passenger1);
        assertTrue(flight.isPaxOnFlight(passenger1));
    }

    @Test
    public void isPaxAlreadyBookedOnFlightFalse(){
        flight.checkInPassenger(passenger1);
        assertFalse(flight.isPaxOnFlight(passenger2));
    }

    @Test
    public void canBookPaxOnFlight(){
        flight.checkInPassenger(passenger1);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void isPaxAssignedToFlight(){
        flight.checkInPassenger(passenger1);
        assertEquals(flight.getFlightNumber(), passenger1.getFlight());
    }

    @Test
    public void canGenerateSeatNumberInRange(){
        int seatNumber = flight.generateSeatNumber();
        assertTrue(0 <= seatNumber && seatNumber <= aircraft.getCapacity());
    }

    @Test
    public void isSeatNumberAvailableTrue(){
        int seatNumber = flight.generateSeatNumber();
        assertTrue(flight.isSeatFree(seatNumber));
    }

    @Test
    public void canDoubleBookSeatFalse(){
        flight.checkInPassenger(passenger1);
        int passenger1Seat = passenger1.getSeat();
        assertFalse(flight.isSeatFree(passenger1Seat));
    }

    @Test
    public void canCheckinPax(){
        assertTrue(flight.checkInPassenger(passenger1));
    }
    @Test
    public void canNotCheckinSamePaxTwice(){
        assertTrue(flight.checkInPassenger(passenger1));
        assertFalse(flight.checkInPassenger(passenger1));
    }
}
