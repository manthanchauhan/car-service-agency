package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentOperatorTimeSlotMappingRepository extends JpaRepository<AppointmentOperatorTimeSlotMapping, Long> {
    AppointmentOperatorTimeSlotMapping findByOperatorIdAndTimeSlotIdAndDate(Long operatorId, Long timeSlotId, Long date);

    List<AppointmentOperatorTimeSlotMapping> findByDateAndTimeSlotId(Long dateEpochMillis, Long timeSlotId);
}
