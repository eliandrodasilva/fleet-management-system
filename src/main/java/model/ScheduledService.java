package model;

import model.enums.ServicePriority;
import model.enums.ServiceStatus;
import model.enums.ServiceType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_services")
public class ScheduledService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceType serviceType;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @Column(name = "estimated_cost", nullable = false, precision = 8, scale = 2)
    private BigDecimal estimatedCost;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_priority", nullable = false)
    private ServicePriority servicePriority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ServiceStatus serviceStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ScheduledService() {}

    public ScheduledService(Vehicle vehicle, ServiceType serviceType, LocalDateTime scheduledDate,
                            BigDecimal estimatedCost, String description,
                            ServicePriority servicePriority, ServiceStatus serviceStatus) {
        this.vehicle = vehicle;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
        this.estimatedCost = estimatedCost;
        this.description = description;
        this.servicePriority = servicePriority;
        this.serviceStatus = serviceStatus;
    }

    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServicePriority getServicePriority() {
        return servicePriority;
    }

    public void setServicePriority(ServicePriority servicePriority) {
        this.servicePriority = servicePriority;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
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
        return "ScheduledService{" +
                "id=" + id +
                ", vehicle=" + (vehicle != null ? vehicle.getId() : null) +
                ", serviceType=" + serviceType +
                ", scheduledDate=" + scheduledDate +
                ", estimatedCost=" + estimatedCost +
                ", description='" + description + '\'' +
                ", servicePriority=" + servicePriority +
                ", serviceStatus=" + serviceStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
