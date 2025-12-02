package com.test.repository;

import com.test.entities.CarEntity;
import com.test.entities.MotorcycleEntity;
import com.test.entities.VehicleEntity;

import java.util.List;
import java.util.Optional;

public class VehicleService {

    private final Repository<VehicleEntity> repository;


    public VehicleService(Repository<VehicleEntity> repository) {
        this.repository = repository;
    }

    public CarEntity createCar(String make, String model, int year, int numDoors) {
        CarEntity car = new CarEntity(make, model, year, numDoors);
        return (CarEntity) repository.save(car);
    }


    public MotorcycleEntity createMotorcycle(String make, String model, int year, boolean hasSidecar) {
        MotorcycleEntity motorcycle = new MotorcycleEntity(make, model, year, hasSidecar);
        return (MotorcycleEntity) repository.save(motorcycle);
    }


    public List<VehicleEntity> getAllVehicles() {
        return repository.findAll();
    }


    public Optional<VehicleEntity> getVehicleById(Long id) {
        return repository.findById(id);
    }


    public VehicleEntity updateVehicle(VehicleEntity vehicle) {
        return repository.save(vehicle);
    }


    public void deleteVehicleById(Long id) {
        repository.deleteById(id);
    }
}