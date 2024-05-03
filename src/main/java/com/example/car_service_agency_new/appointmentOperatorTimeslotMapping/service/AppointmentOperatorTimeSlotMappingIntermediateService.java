package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentOperatorTimeSlotMappingIntermediateService {
    @Autowired
    private AppointmentOperatorTimeSlotMappingService appointmentOperatorTimeSlotMappingService;

    public AppointmentOperatorTimeSlotMapping getByOperatorIdTimeslotIdAndDate(Long operatorId, Long timeslotId, Long dateEpochMillis) {
        return this.appointmentOperatorTimeSlotMappingService.getByOperatorIdTimeslotIdAndDate(operatorId, timeslotId, dateEpochMillis, Boolean.TRUE);
    }

    public List<Long> getOccupiedOperatorIds(Long dateEpochMillis, Long timeSlotId) {
        List<AppointmentOperatorTimeSlotMapping> mappingList = this.appointmentOperatorTimeSlotMappingService.getByDateAndTimeSlotId(dateEpochMillis, timeSlotId, Boolean.TRUE);
        return mappingList.stream().map(AppointmentOperatorTimeSlotMapping::getOperatorId).toList();
    }

    public boolean isOperatorOccupied(Long operatorId, Long timeSlotId, Long dateEpochMillis) {
        return this.appointmentOperatorTimeSlotMappingService.getByOperatorIdTimeslotIdAndDate(operatorId, timeSlotId, dateEpochMillis, Boolean.TRUE) != null;
    }

    public boolean isOperatorOccupied(Long operatorId, Long timeSlotId, Long dateEpochMillis, Long excludedAppointmentId) {
        return this.appointmentOperatorTimeSlotMappingService.getByOperatorIdTimeslotIdAndDateAndAppointmentIdNot(operatorId, timeSlotId, dateEpochMillis, Boolean.TRUE, excludedAppointmentId) != null;
    }

    public AppointmentOperatorTimeSlotMapping getByIdByAppointmentId(Long appointmentId) {
        return this.appointmentOperatorTimeSlotMappingService.getByAppointmentId(appointmentId);
    }

    public void rescheduleAppointment(Long appointmentId, Long newTimeSlotId, Long newDateEpochMillis) {
        this.appointmentOperatorTimeSlotMappingService.rescheduleAppointment(appointmentId, newTimeSlotId, newDateEpochMillis);
    }
}
