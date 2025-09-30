package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", length = 10, nullable = false, unique = true)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", length = 50, nullable = false)
    private VehicleBrand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "model", length = 50, nullable = false)
    private VehicleModel model;

    @Column(name = "model_year", nullable = false)
    private int modelYear;

    @Column(name = "current_kilometers", nullable = false)
    private int currentKilometers;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Vehicle() {}

    public Vehicle(String licensePlate, VehicleModel model, int modelYear, int currentKilometers, VehicleStatus status) {
        this.licensePlate = licensePlate;
        this.brand = model.getVehicleBrand();
        this.model = model;
        this.modelYear = modelYear;
        this.currentKilometers = currentKilometers;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleBrand getBrand() {
        return brand;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
        this.brand = model.getVehicleBrand();
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getCurrentKilometers() {
        return currentKilometers;
    }

    public void setCurrentKilometers(int currentKilometers) {
        this.currentKilometers = currentKilometers;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ", currentKilometers=" + currentKilometers +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}