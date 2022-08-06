public enum AircraftType {

    BOEING752(200, 40000),
    BOEING737(120, 500),
    BOEINGFULL(0, 0);

    private final int capacity;
    private final int totalWeight;

    AircraftType(int capacity, int totalWeight) {
        this.capacity = capacity;
        this.totalWeight = totalWeight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
