import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PilotTest {

    Pilot pilot;

    @Before
    public void before(){
        pilot = new Pilot("Erik", "Captain", "flyboy1");
    }

    @Test
    public void pilotHasName(){
        assertEquals("Erik", pilot.getName());
    }

    @Test
    public void pilotHasRank(){
        assertEquals("Captain", pilot.getRank());
    }

    @Test
    public void pilotHasLicence(){
        assertEquals("flyboy1", pilot.getLicenseNumber());
    }

    @Test
    public void pilotCanFlyPlane(){
        assertEquals("chocks away", pilot.flyAircraft());
    }

}
