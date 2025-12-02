package com.test.entities;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("CAR")
public class CarEntity extends VehicleEntity {

    @Column(name = "num_doors")
    private int numDoors;


    public CarEntity() {
    }


    public CarEntity(String make, String model, int year, int numDoors) {
        super(make, model, year);
        this.numDoors = numDoors;
    }



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