import javax.swing.text.View;
import java.util.Scanner;

public class Main {
    public static void main(){
        int choice;
        boolean runLoop = true;
        TripManager tripManager = new TripManager();
        while(runLoop){
            String banner = """
                ====================================
                       TRIP MANAGEMENT SYSTEM
                ====================================
                """;
            String menu   = """
                1. Register a new trip
                2. View all trips
                3. Calculate total revenue
                4. View trip statistics
                5. Search trip by ID
                6. Exit
                """;
            System.out.println(banner);
            System.out.println(menu);

            Scanner reader = new Scanner(System.in);
            System.out.print("Enter your choice[1-6]: ");
            choice = Integer.parseInt(reader.nextLine());

            switch (choice){

                case 1:
                    try {
                        Trip trip = new Trip();
                        trip = trip.inputInformation(reader);
                        tripManager.registerTrip(trip);
                        System.out.println("Trip has been recorded!");
                        break;

                    }catch (Exception e){
                        System.out.println("Trip could not be recorded!");
                        System.out.println(e.getMessage());
                    }


                case 2:
                    try {
                        tripManager.displayTrips();
                        break;

                    } catch (IllegalStateException e) {
                        System.out.println("There is no trip registered, register to continue");
                    }


                case 3:
                    try {
                        tripManager.calculateRevenue();
                        break;
                    }catch (Exception e){
                        System.out.println("There is no trip registered, register one to continue!");
                    }


                case 4:
                    tripManager.showStatistics();
                    break;

                case 5:
                    tripManager.searchTrip();
                    break;

                case 6:
                    System.out.println("Exiting!");
                    runLoop = false;
                    break;


                default:
                    System.out.println("Option selected unknown!");


            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            reader.close();
        }


    }
}