package com.example.car_service_agency_new.appointment.domain;

import com.example.car_service_agency_new.util.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends AbstractBaseEntity {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "vehicle_model_id")
    private Long vehicleModelId;

    @Column(name = "status")
    private String status;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "notes")
    private String notes;

    public Appointment(Long userId, Long vehicleModelId, String status, String notes) {
        this.uuid = UUID.randomUUID().toString();

        this.userId = userId;
        this.vehicleModelId = vehicleModelId;
        this.status = status;
        this.notes = notes;
    }
}
