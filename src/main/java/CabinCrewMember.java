public class CabinCrewMember {

    private String name;
    private String rank;

    public CabinCrewMember(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }


    public String getName() {
        return this.name;
    }

    public String getRank() {
        return this.rank;
    }

    public String makeAnnouncement() {
        return "Welcome Onboard!";
    }
}
