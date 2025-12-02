package com.test.entities;

import jakarta.persistence.*;

/**
 * CarEntity represents a car vehicle
 * Extends VehicleEntity and adds car-specific properties
 */
@Entity
@DiscriminatorValue("CAR")
public class CarEntity extends VehicleEntity {

    @Column(name = "num_doors")
    private int numDoors;

    /**
     * Default constructor required by JPA
     */
    public CarEntity() {
    }

    /**
     * Constructor with all fields
     * @param make The make of the car
     * @param model The model of the car
     * @param year The year of the car
     * @param numDoors The number of doors
     */
    public CarEntity(String make, String model, int year, int numDoors) {
        super(make, model, year);
        this.numDoors = numDoors;
    }

    // Getters and Setters

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + getId() +
                ", make='" + getMake() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", numDoors=" + numDoors +
                '}';
    }
}