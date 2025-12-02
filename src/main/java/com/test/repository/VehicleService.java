package com.test.repository;

import com.test.entities.CarEntity;
import com.test.entities.MotorcycleEntity;
import com.test.entities.VehicleEntity;

import java.util.List;
import java.util.Optional;

public class VehicleService {

    private final Repository<VehicleEntity> repository;

    // Constructor - depends ONLY on the Repository interface
    public VehicleService(Repository<VehicleEntity> repository) {
        this.repository = repository;
    }

    // Create a new car
    public CarEntity createCar(String make, String model, int year, int numDoors) {
        CarEntity car = new CarEntity(make, model, year, numDoors);
        return (CarEntity) repository.save(car);
    }

    // Create a new motorcycle
    public MotorcycleEntity createMotorcycle(String make, String model, int year, boolean hasSidecar) {
        MotorcycleEntity motorcycle = new MotorcycleEntity(make, model, year, hasSidecar);
        return (MotorcycleEntity) repository.save(motorcycle);
    }

    // Get all vehicles
    public List<VehicleEntity> getAllVehicles() {
        return repository.findAll();
    }

    // Get a vehicle by ID
    public Optional<VehicleEntity> getVehicleById(Long id) {
        return repository.findById(id);
    }

    // Update an existing vehicle
    public VehicleEntity updateVehicle(VehicleEntity vehicle) {
        return repository.save(vehicle);
    }

    // Delete a vehicle by ID
    public void deleteVehicleById(Long id) {
        repository.deleteById(id);
    }
}