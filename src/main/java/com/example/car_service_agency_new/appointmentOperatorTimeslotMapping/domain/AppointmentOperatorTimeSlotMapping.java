package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain;

import com.example.car_service_agency_new.util.AbstractBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;

@Entity
@Table(name = "appointment_operator_time_slot_mapping")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentOperatorTimeSlotMapping extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "operator_id")
    private Long operatorId;

    @Column(name = "time_slot_id")
    private Long timeSlotId;

    @Column(name = "date")
    private Long date;

    @Column(name = "is_active")
    private Boolean isActive;
}