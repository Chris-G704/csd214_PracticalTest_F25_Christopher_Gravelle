package ca.saultcollege;

import com.test.entities.VehicleEntity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Vehicle Inventory System ===");
        System.out.println("Select data source:");
        System.out.println("1. In-Memory");
        System.out.println("2. MySQL Database");
        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        Repository<VehicleEntity> repository;
        EntityManagerFactory emf = null;

        if (choice == 1) {
            // The In-Memory Repository, contains vehicles
            repository = new InMemoryVehicleRepository();
            System.out.println("Using In-Memory storage.\n");
        } else if (choice == 2) {
            //The MySQL Repository, contains vehicles
            emf = Persistence.createEntityManagerFactory("VehiclePU");
            repository = new MySQLVehicleRepository(emf);
            System.out.println("Using MySQL database.\n");
        } else {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            return;
        }

        // Dependency Injection
        VehicleService vehicleService = new VehicleService(repository);
        App app = new App(vehicleService);

        // Runs the application
        app.run();

        // Cleanup
        if (emf != null) {
            emf.close();
        }
        scanner.close();
    }
}