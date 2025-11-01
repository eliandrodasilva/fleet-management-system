package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_brands", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class VehicleBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public VehicleBrand() {}

    public VehicleBrand(String name) {
        this.name = name.trim().toUpperCase();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
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
        return "VehicleBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
