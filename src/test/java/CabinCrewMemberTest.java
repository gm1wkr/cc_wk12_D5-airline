import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CabinCrewMemberTest {

    CabinCrewMember cabinCrewMember;

    @Before
    public void before(){
        cabinCrewMember = new CabinCrewMember("Timmy", "Flight Attendant");
    }

    @Test
    public void cabinCrewHasName(){
        assertEquals("Timmy", cabinCrewMember.getName());
    }

    @Test
    public void cabinCrewHasRank(){
        assertEquals("Flight Attendant", cabinCrewMember.getRank());
    }

    @Test
    public void canMakeAnnouncementsToPax(){
        assertEquals("Welcome Onboard!", cabinCrewMember.makeAnnouncement());
    }
}
