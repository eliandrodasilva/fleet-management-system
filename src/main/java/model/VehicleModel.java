package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vehicle_models", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "brand_id"})
})
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private VehicleBrand brand;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public VehicleModel() {}

    public VehicleModel(String name, VehicleBrand brand) {
        this.name = name.trim().toUpperCase();
        this.brand = brand;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
    }

    public VehicleBrand getBrand() { return brand; }

    public void setBrand(VehicleBrand brand) { this.brand = brand; }

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
        return "VehicleModel{" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", brand=" + brand + '\'' +
                '}';
    }
}
