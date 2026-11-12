import java.util.Scanner;

public class Trip {
    private int tripId;
    private String customerName;
    private String startingPoint;
    private String destination;
    private double distance;
    private double pricePerKm;
    private int estimatedDuration;
    private double estimatedFare;
    private double speed;
    private String tripClassification;

    public Trip(){};

    private Trip( Builder builder){
        this.customerName = builder.customerName;
        this.startingPoint = builder.startingPoint;
        this.destination = builder.destination;
        this.distance = builder.distance;
        this.speed = builder.speed;
        this.pricePerKm = builder.pricePerKm;
    }

    public static class Builder {
        private int tripId;
        private String customerName;
        private String startingPoint;
        private String destination;
        private double distance;
        private double pricePerKm;
        private int estimatedDuration;
        private double estimatedFare;
        private double speed;
        private String tripClassification;

        Builder() {
        }
        public Builder customerName(String customerName){
            this.customerName = customerName;
            return this;

        }
        public Builder startingPoint(String startingPoint){
            this.startingPoint = startingPoint;
            return this;

        }
        public Builder destination(String destination){
            this.destination = destination;
            return this;

        }
        public Builder distance(double distance){
            this.distance = distance;
            return this;
        }
        public Builder speed(double speed){
            this.speed = speed;
            return this;
        }

        public Builder pricePerKm(double pricePerKm){
            this.pricePerKm = pricePerKm;
            return this;
        }

        public Trip build(){
            if(customerName == null || startingPoint == null || destination == null){
                throw new IllegalStateException("Name, starting point and destination must be known!");
            }
            Trip trip = new Trip(this);
            trip.calculateFare();
            return trip;

        }

    }

    public void assignId(int currentID){
        this.tripId = currentID;
    }


    public void calculateDuration(){
        try {
            this.estimatedDuration = (int) ((this.distance / this.speed) * 60);
        }catch (ArithmeticException e){
            System.out.println("Duration could not be calculated since speed is not defined");
        }
    }
    public void calculateFare(){
        this.estimatedFare = pricePerKm * distance;
    }

    public void classifyTrip(){
        if (distance < 10) {

            this.tripClassification = "Short trip";

        }else if (distance > 10 && distance < 50) {

            this.tripClassification = "Medium trip";

        }else {
            this.tripClassification = "Long trip";
        }
    }


    public double getFare(){
        return this.estimatedFare;
    }
    public int getTripId(){
        return this.tripId;
    }
    public double getDistance(){ return this.distance; }

    public Trip inputInformation(Scanner scanner){

        System.out.println("""
                Enter the customer name, starting and destination points as well
                as the distance of the trip and the price per km rate.
                """);
        System.out.print("Enter the customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter the journey starting point: ");
        String startingPoint = scanner.nextLine();
        System.out.print("Enter the journey destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter the speed: ");
        double speed = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter the journey distance: ");
        double distance = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter the journey price per unit rate: ");
        int pricePerKm = Integer.parseInt(scanner.nextLine().trim());


        Builder builder = new Builder();
        return builder.customerName(customerName)
                .startingPoint(startingPoint)
                .destination(destination)
                .distance(distance)
                .speed(speed)
                .pricePerKm(pricePerKm)
                .build();



    }
    public void displayInformation(){
        String information = String.format(
                """
                ID:             %d
                Customer:       %s
                Route:          %s -> %s
                Distance:       %.2f km
                Speed:          %.2f km/h
                Duration:       %d
                Estimated Fare: %,.2f RWF
                Classification: %s
                """,
                this.tripId,
                this.customerName,
                this.startingPoint,
                this.destination,
                this.distance,
                this.speed,
                this.estimatedDuration,
                this.estimatedFare,
                this.tripClassification
        );
        System.out.println(information);

    }

}

