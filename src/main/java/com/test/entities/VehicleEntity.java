package com.test.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type")
public abstract class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    // My Default constructor for the vehicle entity
    public VehicleEntity() {
    }

    // Constructor with fields
    public VehicleEntity(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters and Setters
    public Long getId() {
        return id; // get the id
    }

    public void setId(Long id) {
        this.id = id; // return the id
    }

    public String getMake() {
        return make; // get the make of the vehicle
    }

    public void setMake(String make) {
        this.make = make;// set the make value of the vehicle
    }

    public String getModel() {
        return model; // return the make of the vehicle
    }

    public void setModel(String model) {
        this.model = model; // set the model of the vehicle
    }

    public int getYear() {
        return year; // get the year of the vehicle
    }

    public void setYear(int year) {
        this.year = year; // set the year of the vehicle
    }
}
