package com.test.entities;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MOTORCYCLE") // discriminator value, Motorcycle
public class MotorcycleEntity extends VehicleEntity {

    @Column(name = "has_sidecar") // the column is for side doors, this should show up results for sidecar
    private boolean hasSidecar;

    // Default constructor
    public MotorcycleEntity() {
    }

    // Constructor with fields
    public MotorcycleEntity(String make, String model, int year, boolean hasSidecar) {
        super(make, model, year);
        this.hasSidecar = hasSidecar;
    }

    // Getters and Setters
    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
}