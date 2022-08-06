import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.*;

public class FlightManTest {

    Aircraft aircraft;
    Pilot pilot;
    Passenger passenger1;
    Passenger passenger2;
    Passenger passenger3;
    Passenger passengerOverWeight;
    FlightMan flightMan;

    @Before
    public void before(){

        String departureTimeString = "06 Aug 2022 1100";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm", Locale.ENGLISH);
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);
        pilot = new Pilot("Erik", "Captain", "abc123");
        aircraft = new Aircraft(AircraftType.BOEING752.getCapacity(), AircraftType.BOEING752.getTotalWeight());
        passenger1 = new Passenger("James", 1);
        passenger2 = new Passenger("Sumin", 2);
        passenger3 = new Passenger("Sally", 2);
        passengerOverWeight = new Passenger("Boris", 10);
        flightMan = new FlightMan(aircraft, pilot, "SB1", "ABZ", "IBA", departureTime);
    }

    @Test
    public void flightStartsWithNoPassengers(){
        assertEquals(0, flightMan.getNumberOfPassengers());
    }

    @Test
    public void canGetWeightReservedForBags(){
        assertEquals(20000, flightMan.weightAvailableForBags());
    }

    @Test
    public void canGetPaxTotalBagWeight(){
        flightMan.checkInPassenger(passenger1);
        assertEquals(10, flightMan.getPaxBagWeight(passenger1));
    }

    @Test
    public void canGetPaxBaggageAllowance(){
        assertEquals(20, flightMan.getBaggageAllowance());
    }

    @Test
    public void isPaxWithinBaggageLimitFalse(){
        assertFalse(flightMan.withinBaggageLimit(passengerOverWeight));
    }
    @Test
    public void isPaxWithinBaggageLimitTrue(){
        assertTrue(flightMan.withinBaggageLimit(passenger1));
    }

    @Test
    public void canGetTotalBaggageBooked(){
        flightMan.checkInPassenger(passenger1);
        flightMan.checkInPassenger(passenger2);
        flightMan.checkInPassenger(passenger3);
        assertEquals(60, flightMan.getTotalBaggageWeight());
    }


    @Test
    public void canGetRemainingBaggageWeight(){
        flightMan.checkInPassenger(passenger1);
        flightMan.checkInPassenger(passenger2);
        flightMan.checkInPassenger(passenger3);
        assertEquals(19940, flightMan.getTotalBaggageWeightRemaining());
    }
}
