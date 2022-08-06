import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PassengerTest {

    Passenger passenger;

    @Before
    public void before(){
        passenger = new Passenger("Bill Gates", 2);
    }

    @Test
    public void passengerHasName(){
        assertEquals("Bill Gates", passenger.getName());
    }

    @Test
    public void canGetNumberOfBags(){
        assertEquals(2, passenger.getNumberOfBags());
    }

    @Test
    public void startsWithoutFlightAssignment(){
        assertNull(passenger.getFlight());
    }

    @Test
    public void startsWithoutSeatAssignment(){
        assertEquals(-1, passenger.getSeat());
    }
}
