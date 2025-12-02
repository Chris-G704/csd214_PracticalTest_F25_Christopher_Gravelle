package com.test.entities;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CAR") // this is the value of the car, when the value is car, it will show the value "CAR"
public class CarEntity extends VehicleEntity {

    @Column(name = "num_doors")
    private int numvehicleDoors;

    // Default constructor
    public CarEntity() {
    }

    // Constructor with fields
    public CarEntity(String make, String model, int year, int numDoors) {
        super(make, model, year);
        this.numvehicleDoors = numDoors;
    }

    // Getters and Setters
    public int getNumDoors() { // get the number of doors
        return numvehicleDoors; // return the number of doors
    }

    public void setNumDoors(int numDoors) { //set the number of doors
        this.numvehicleDoors = numDoors; // value of the doors
    }
}

