import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.*;

public class FlightManagerTest {

    Aircraft aircraft;
    Pilot pilot;
    Passenger passenger1;
    Passenger passenger2;
    Passenger passenger3;
    Passenger passengerOverWeight;
    FlightManager flightManager;

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
        flightManager = new FlightManager(aircraft, pilot, "SB1", "ABZ", "IBA", departureTime);
    }

    @Test
    public void flightStartsWithNoPassengers(){
        assertEquals(0, flightManager.getNumberOfPassengers());
    }

    @Test
    public void canGetWeightReservedForBags(){
        assertEquals(20000, flightManager.weightAvailableForBags());
    }

    @Test
    public void canGetPaxTotalBagWeight(){
        flightManager.checkInPassenger(passenger1);
        assertEquals(10, flightManager.getPaxBagWeight(passenger1));
    }

    @Test
    public void canGetPaxBaggageAllowance(){
        assertEquals(20, flightManager.getBaggageAllowance());
    }

    @Test
    public void isPaxWithinBaggageLimitFalse(){
        assertFalse(flightManager.withinBaggageLimit(passengerOverWeight));
    }
    @Test
    public void isPaxWithinBaggageLimitTrue(){
        assertTrue(flightManager.withinBaggageLimit(passenger1));
    }

    @Test
    public void canGetTotalBaggageBooked(){
        flightManager.checkInPassenger(passenger1);
        flightManager.checkInPassenger(passenger2);
        flightManager.checkInPassenger(passenger3);
        assertEquals(60, flightManager.getTotalBaggageWeight());
    }


    @Test
    public void canGetRemainingBaggageWeight(){
        flightManager.checkInPassenger(passenger1);
        flightManager.checkInPassenger(passenger2);
        flightManager.checkInPassenger(passenger3);
        assertEquals(19940, flightManager.getTotalBaggageWeightRemaining());
    }
}
