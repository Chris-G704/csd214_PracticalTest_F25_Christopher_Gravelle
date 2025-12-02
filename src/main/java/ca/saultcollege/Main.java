package ca.saultcollege;

import com.test.entities.VehicleEntity;
import com.test.repository.InMemoryVehicleRepository;
import com.test.repository.MySQLVehicleRepository;
import com.test.repository.Repository;
import com.test.repository.VehicleService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

/**
 * Main entry point - Composition Root
 * Demonstrates Inversion of Control and Dependency Injection
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt user to choose data source
        System.out.println("=================================");
        System.out.println("  Vehicle Inventory System");
        System.out.println("=================================");
        System.out.println("Select data source:");
        System.out.println("1. In-Memory");
        System.out.println("2. MySQL Database");
        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        Repository<VehicleEntity> repository;
        EntityManagerFactory emf = null;

        // Step 2: Choose the correct repository based on user choice
        if (choice == 1) {
            // In-Memory Repository
            repository = new InMemoryVehicleRepository();
            System.out.println("\n✓ Using in-memory storage.\n");
        } else if (choice == 2) {
            // MySQL Repository
            try {
                emf = Persistence.createEntityManagerFactory("VehiclePU");
                repository = new MySQLVehicleRepository(emf);
                System.out.println("\n✓ Connected to MySQL database.\n");
            } catch (Exception e) {
                System.err.println("Failed to connect to MySQL database.");
                System.err.println("Error: " + e.getMessage());
                System.err.println("\nPlease ensure:");
                System.err.println("1. Docker container is running (docker-compose up -d)");
                System.err.println("2. MySQL is accessible on localhost:3306");
                scanner.close();
                return;
            }
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

        System.out.println("\nThank you for using Vehicle Inventory System!");
    }
}