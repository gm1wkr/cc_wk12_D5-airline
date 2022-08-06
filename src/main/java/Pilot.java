public class Pilot extends CabinCrewMember{

    private String licenseNumber;

    public Pilot(String name, String rank, String licenseNumber) {
        super(name, rank);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }


    public String flyAircraft() {
        return "chocks away";
    }
}
