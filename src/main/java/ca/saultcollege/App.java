package ca.saultcollege;

import com.test.entities.CarEntity;
import com.test.entities.MotorcycleEntity;
import com.test.entities.VehicleEntity;
import com.test.repository.VehicleService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private final VehicleService vehicleService;
    private final Scanner scanner;

    // Constructor receives VehicleService via dependency injection
    public App(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.scanner = new Scanner(System.in);
    }

    // Main application loop
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
          // give the user choices, 1-5.
            switch (choice) {
                case 1 -> addVehicle();
                case 2 -> listAllVehicles();
                case 3 -> updateVehicle();
                case 4 -> deleteVehicle();
                case 5 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again."); // this is only when the user puts something that is not recognized by the system
            }

            System.out.println();
        }

        scanner.close();
    }

    // These are the display menu options
    private void displayMenu() {
        System.out.println("Vehicle Inventory Management");
        System.out.println("1. Add Vehicle");
        System.out.println("2. List All Vehicles");
        System.out.println("3. Update Vehicle");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. Quit progam");
    }

    // Add a new vehicle (car or motorcycle)
    private void addVehicle() {
        System.out.println("\n Add Vehicle");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        int type = getIntInput("Select vehicle type: ");

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        int year = getIntInput("Enter year: ");

        if (type == 1) {
            // Add Car
            int numDoors = getIntInput("Enter number of doors: ");
            CarEntity car = vehicleService.createCar(make, model, year, numDoors);
            System.out.println("Car added successfully! ID: " + car.getId());
        } else if (type == 2) {
            // Add Motorcycle
            System.out.print("Has sidecar? (yes/no): ");
            String input = scanner.nextLine();
            boolean hasSidecar = input.equalsIgnoreCase("yes");
            MotorcycleEntity motorcycle = vehicleService.createMotorcycle(make, model, year, hasSidecar);
            System.out.println("Motorcycle added successfully! ID: " + motorcycle.getId());
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    // List all vehicles in inventory
    private void listAllVehicles() {
        System.out.println("\nAll Vehicles");
        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            for (VehicleEntity vehicle : vehicles) {
                if (vehicle instanceof CarEntity car) {
                    System.out.println("[CAR] ID: " + car.getId() +
                            "  " + car.getMake() + " " + car.getModel() +
                            " " + car.getYear() + ")" +
                            "  Doors: " + car.getNumDoors());
                } else if (vehicle instanceof MotorcycleEntity motorcycle) {
                    System.out.println("[MOTORCYCLE] ID: " + motorcycle.getId() +
                            "  " + motorcycle.getMake() + " " + motorcycle.getModel() +
                            " " + motorcycle.getYear() + ")" +
                            "  Sidecar: " + (motorcycle.isHasSidecar() ? "Yes" : "No"));
                }
            }
        }
    }

    // Update an existing vehicle
    private void updateVehicle() {
        System.out.println("\n Update Vehicle");
        long id = getIntInput("Enter vehicle ID to update: ");

        Optional<VehicleEntity> optionalVehicle = vehicleService.getVehicleById(id);

        if (optionalVehicle.isEmpty()) {
            System.out.println("Vehicle not found with ID: " + id);
            return;
        }

        VehicleEntity vehicle = optionalVehicle.get();
        System.out.println("Current: " + getVehicleDisplay(vehicle));

        System.out.print("Enter new make (or press Enter to keep current): ");
        String make = scanner.nextLine();
        if (!make.isEmpty()) {
            vehicle.setMake(make);
        }

        System.out.print("Enter new model (or press Enter to keep current): ");
        String model = scanner.nextLine();
        if (!model.isEmpty()) {
            vehicle.setModel(model);
        }

        System.out.print("Enter new year (or 0 to keep current): ");
        int year = getIntInput("");
        if (year != 0) {
            vehicle.setYear(year);
        }

        // Update type-specific fields
        if (vehicle instanceof CarEntity car) {
            System.out.print("Enter new number of doors (or 0 to keep current): ");
            int numDoors = getIntInput("");
            if (numDoors != 0) {
                car.setNumDoors(numDoors);
            }
        } else if (vehicle instanceof MotorcycleEntity motorcycle) {
            System.out.print("Has sidecar? (yes/no/skip): ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("skip")) {
                motorcycle.setHasSidecar(input.equalsIgnoreCase("yes"));
            }
        }

        vehicleService.updateVehicle(vehicle);
        System.out.println("Vehicle updated successfully!");
    }

    // Delete a vehicle
    private void deleteVehicle() {
        System.out.println("\n--- Delete Vehicle ---");
        long id = getIntInput("Enter vehicle ID to delete: ");

        Optional<VehicleEntity> vehicle = vehicleService.getVehicleById(id);

        if (vehicle.isEmpty()) {
            System.out.println("Vehicle not found with ID: " + id);
            return;
        }

        vehicleService.deleteVehicleById(id);
        System.out.println("Vehicle deleted successfully!");
    }

    // Helper method to get integer input with validation
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Helper method to display vehicle info
    private String getVehicleDisplay(VehicleEntity vehicle) {
        if (vehicle instanceof CarEntity car) {
            return "[CAR] " + car.getMake() + " " + car.getModel() +
                    " (" + car.getYear() + ") | Doors: " + car.getNumDoors();
        } else if (vehicle instanceof MotorcycleEntity motorcycle) {
            return "[MOTORCYCLE] " + motorcycle.getMake() + " " + motorcycle.getModel() +
                    " (" + motorcycle.getYear() + ") | Sidecar: " +
                    (motorcycle.isHasSidecar() ? "Yes" : "No");
        }
        return vehicle.toString();
    }
}
