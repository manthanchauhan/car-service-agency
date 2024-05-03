package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AppointmentOperatorTimeSlotMappingRepository extends JpaRepository<AppointmentOperatorTimeSlotMapping, Long> {
    AppointmentOperatorTimeSlotMapping findByOperatorIdAndTimeSlotIdAndDateAndIsActive(Long operatorId, Long timeSlotId, Long date, Boolean isActive);

    List<AppointmentOperatorTimeSlotMapping> findByDateAndTimeSlotIdAndIsActive(Long dateEpochMillis, Long timeSlotId, Boolean isActive);

    Optional<AppointmentOperatorTimeSlotMapping> findByAppointmentId(Long appointmentId);

    @Query(
            "SELECT a " +
            "FROM AppointmentOperatorTimeSlotMapping a " +
            "LEFT JOIN Timeslot t ON a.timeSlotId = t.id " +
            "WHERE a.operatorId = ?1 AND a.date >= ?2 AND a.isActive = TRUE " +
            "ORDER BY a.date ASC, t.startHour ASC " +
            "LIMIT ?3 " +
            "OFFSET ?4 "
    )
    List<AppointmentOperatorTimeSlotMapping> getPaginatedListByOperatorIdAndDateGreaterThanEqual(Long operatorId, Long dateEpochMillis, Long limit, Long offset);

    List<AppointmentOperatorTimeSlotMapping> findByDateAndOperatorIdAndIsActive(Long dateEpochMillis, Long operatorId, Boolean isActive);
}
