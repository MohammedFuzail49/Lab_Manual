class UndergroundSystem {

    class CheckIn {
        String stationName;
        int time;

        CheckIn(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    class Trip {
        int totalTime;
        int tripCount;

        Trip(int totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }
    }

    private Map<Integer, CheckIn> checkIns;
    private Map<String, Trip> trips;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        trips = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkIns.get(id);

        String key = checkIn.stationName + "->" + stationName;
        int travelTime = t - checkIn.time;

        Trip trip = trips.getOrDefault(key, new Trip(0, 0));
        trip.totalTime += travelTime;
        trip.tripCount++;

        trips.put(key, trip);
        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "->" + endStation;
        Trip trip = trips.get(key);

        return (double) trip.totalTime / trip.tripCount;
    }
}
