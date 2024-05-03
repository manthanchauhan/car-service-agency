package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AppointmentOperatorTimeSlotMappingRepository extends JpaRepository<AppointmentOperatorTimeSlotMapping, Long> {
    AppointmentOperatorTimeSlotMapping findByOperatorIdAndTimeSlotIdAndDateAndIsActive(Long operatorId, Long timeSlotId, Long date, Boolean isActive);

    List<AppointmentOperatorTimeSlotMapping> findByDateAndTimeSlotIdAndIsActive(Long dateEpochMillis, Long timeSlotId, Boolean isActive);

    Optional<AppointmentOperatorTimeSlotMapping> findByAppointmentId(Long appointmentId);
}
