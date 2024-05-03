package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentOperatorTimeSlotMappingService {
    @Autowired
    private AppointmentOperatorTimeSlotMappingRepository appointmentOperatorTimeSlotMappingRepository;

    public AppointmentOperatorTimeSlotMapping getByOperatorIdTimeslotIdAndDate(Long operatorId, Long timeslotId, Long dateEpochMillis, Boolean isActive) {
        return this.appointmentOperatorTimeSlotMappingRepository.findByOperatorIdAndTimeSlotIdAndDateAndIsActive(operatorId, timeslotId, dateEpochMillis, isActive);
    }

    public List<AppointmentOperatorTimeSlotMapping> getByDateAndTimeSlotId(Long dateEpochMillis, Long timeSlotId, Boolean isActive) {
        return this.appointmentOperatorTimeSlotMappingRepository.findByDateAndTimeSlotIdAndIsActive(dateEpochMillis, timeSlotId, isActive);
    }

    public AppointmentOperatorTimeSlotMapping getByAppointmentId(Long appointmentId) {
        return this.appointmentOperatorTimeSlotMappingRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Something went wrong!"));
    }

    public List<AppointmentOperatorTimeSlotMapping> getPaginatedListByOperatorIdAndDateGreaterThanEqual(Long operatorId, Long dateEpochMillis, Long page, Long limit) {
        return this.appointmentOperatorTimeSlotMappingRepository.getPaginatedListByOperatorIdAndDateGreaterThanEqual(operatorId, dateEpochMillis, limit, (page - 1) * limit);
    }

    public List<AppointmentOperatorTimeSlotMapping> getByDateAndOperatorIdAndIsActive(Long dateEpochMillis, Long operatorId, Boolean isActive) {
return this.appointmentOperatorTimeSlotMappingRepository.findByDateAndOperatorIdAndIsActive(dateEpochMillis, operatorId, isActive);
    }
}
