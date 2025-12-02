package ca.saultcollege;

import com.test.repository.InMemoryVehicleRepository;
import com.test.repository.MySQLVehicleRepository;
import com.test.repository.VehicleService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt user to choose data source
        System.out.println("--- Vehicle Inventory System ---");
        System.out.println("Select data source:");
        System.out.println("1. In-Memory");
        System.out.println("2. MySQL Database");
        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        InMemoryVehicleRepository repository;
        EntityManagerFactory emf = null;

        // Step 2: Choose the correct repository based on user choice
        if (choice == 1) {
            // In-Memory Repository
            repository = new InMemoryVehicleRepository();
            System.out.println("Currently using in-memory storage.\n");
        } else if (choice == 2) {
            // MySQL Repository
            emf = Persistence.createEntityManagerFactory("VehiclePU");
            repository = new MySQLVehicleRepository(emf);
            System.out.println("Using MySQL database.\n");
        } else {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            return;
        }

        // Step 3: Instantiate VehicleService, injecting the repository
        VehicleService vehicleService = new VehicleService(repository);

        // Step 4: Instantiate App, injecting the VehicleService
        App app = new App(vehicleService);

        // Step 5: Call run() to start the application
        app.run();

        // Cleanup resources
        if (emf != null) {
            emf.close();
        }
        scanner.close();
    }
}