import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AircraftTest {
    Aircraft aircraft;

    @Before
    public void before(){
        aircraft = new Aircraft(AircraftType.BOEING737.getCapacity(), AircraftType.BOEING737.getTotalWeight());
    }

    @Test
    public void canGetAircraftCapacity(){
        assertEquals(120, aircraft.getCapacity());
    }

    @Test
    public void canGetAircraftWeight(){
        assertEquals(500, aircraft.getTotalWeight());
    }

}
