import java.util.Scanner;

public class TripManager {
    private final Trip[] trips = new Trip[10];
    private int numberOfRegisteredTrips;
    private double totalRevenue;
    private double totalDistance;
    private double averageDistance;
    private double shortestTrip;
    private double longestTrip;
    public void registerTrip(Trip trip){
        for(int i = 0; i < this.trips.length; i++){
            if(trips[this.trips.length - 1] != null ){
                throw new IllegalStateException("The trip manager is full");
            }

            if(trips[i] == null){
                this.trips[i] = trip;
                if(i == 0){
                    this.trips[i].assignId(1);
                    this.trips[i].calculateDuration();
                    this.trips[i].classifyTrip();

                }
                else {
                    this.trips[i].assignId(this.trips[i-1].getTripId());
                    this.trips[i].calculateDuration();
                    this.trips[i].classifyTrip();
                }
                break;
            }
        }

    }
    public int calcNumberOfRegisteredTrips (){
        int count = 0;
        for (Trip trip : this.trips) {
            if (trip != null) {
                count++;
            }

        }
        return this.numberOfRegisteredTrips = count;


    }
    public void displayTrips(){
        System.out.println("""
                ============== REGISTERED TRIPS ==============
                """);
        for (Trip trip : this.trips) {
            if (this.trips[0] == null) {
                throw new IllegalStateException("Trips array is empty!");
            }
            if (trip != null) {
                trip.displayInformation();
                System.out.println("-----------------------------------------------");

            } else {
                break;


            }

        }

    }

    public void calculateRevenue(){
        for(Trip trip: this.trips){
            if(this.trips[0] == null){
                throw new IllegalStateException("Array must have at least a trip to compute revenue");

            }
            if(trip != null){
                this.totalRevenue += trip.getFare();

            }

        }
        System.out.println("The total revenue is " + this.totalRevenue);

    }

    public double calculateDistance(){
        for(Trip trip: this.trips){
            if(this.trips[0] == null){
                throw new IllegalStateException("Array must have at least a trip to compute revenue");

            }
            if(trip != null){
                this.totalDistance += trip.getDistance();

            }

        }
        return this.totalDistance;
    }

    public double calculateAverageDistance(){
        try {
            this.averageDistance = this.totalDistance / this.numberOfRegisteredTrips;
            return this.averageDistance;

        } catch (ArithmeticException e) {
            System.out.println("Invalid number of trips, register a trip before calculating the average!");
        }
        return  this.averageDistance;


    }

    public double findShortestTrip(){
        int revolveIndex = 0;
        double target = this.trips[0].getDistance();
        for (Trip trip : this.trips) {
            if (trip.getDistance() < this.trips[revolveIndex].getDistance()) {
                {
                    target = trip.getDistance();
                    revolveIndex++;
                }

            }


        }
        this.shortestTrip = target;
        return this.shortestTrip;

    }

    public double findLongestTrip(){
        int revolveIndex = 0;
        double target = this.trips[0].getDistance();
        for (Trip trip : this.trips) {
            if (trip.getDistance() > this.trips[revolveIndex].getDistance()) {
                {
                    target = trip.getDistance();
                    revolveIndex++;
                }

            }


        }
        this.longestTrip = target;
        return this.longestTrip;

    }

    public void showStatistics(){
        this.numberOfRegisteredTrips = this.calcNumberOfRegisteredTrips();
        this.totalDistance = this.calculateDistance();
        this.totalDistance = this.calculateAverageDistance();
        this.shortestTrip = this.findShortestTrip();
        this.longestTrip = this.findLongestTrip();

        String statistics = String.format("""
        ============== TRIPS STATISTICS ==============

        | %-20s | %18s |
        |----------------------|--------------------|
        | %-20s | %18d |
        | %-20s | %,18.2f |
        | %-20s | %,18.2f |
        | %-20s | %,18.2f |
        | %-20s | %,18.2f |
        """,
                "Statistic", "Expected Result",
                "Number of trips", this.numberOfRegisteredTrips,
                "Total distance", this.totalDistance,
                "Average distance", this.averageDistance,
                "Longest trip", this.longestTrip,
                "Shortest trip", this.shortestTrip
        );
        System.out.println(statistics);

    }

    public void searchTrip(){

        boolean tripFound = false;
        Trip foundTrip = null;
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the id for the trip: ");
        int tripID = reader.nextInt();
        for(Trip trip : trips){
            if(trip.getTripId() == tripID){
                tripFound = true;
                foundTrip = trip;
                break;
            }

        }
        if(tripFound){
            foundTrip.displayInformation();

        }

        System.out.println("The trip with provided ID could no be found");
        reader.close();

    }








}
