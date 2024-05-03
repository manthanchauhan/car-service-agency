package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentOperatorTimeSlotMappingService {
    @Autowired
    private AppointmentOperatorTimeSlotMappingRepository appointmentOperatorTimeSlotMappingRepository;

    public AppointmentOperatorTimeSlotMapping getByOperatorIdTimeslotIdAndDate(Long operatorId, Long timeslotId, Long dateEpochMillis) {
        return this.appointmentOperatorTimeSlotMappingRepository.findByOperatorIdAndTimeSlotIdAndDate(operatorId, timeslotId, dateEpochMillis);
    }

    public List<AppointmentOperatorTimeSlotMapping> getByDateAndTimeSlotId(Long dateEpochMillis, Long timeSlotId) {
        return this.appointmentOperatorTimeSlotMappingRepository.findByDateAndTimeSlotId(dateEpochMillis, timeSlotId);
    }
}
