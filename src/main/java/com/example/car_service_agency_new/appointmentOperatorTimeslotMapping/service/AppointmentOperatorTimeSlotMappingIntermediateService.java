package com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentOperatorTimeSlotMappingIntermediateService {
    @Autowired
    private AppointmentOperatorTimeSlotMappingService appointmentOperatorTimeSlotMappingService;

    public List<Long> getOccupiedOperatorIds(Long dateEpochMillis, Long timeSlotId) {
        List<AppointmentOperatorTimeSlotMapping> mappingList = this.appointmentOperatorTimeSlotMappingService.getByDateAndTimeSlotId(dateEpochMillis, timeSlotId, Boolean.TRUE);
        return mappingList.stream().map(AppointmentOperatorTimeSlotMapping::getOperatorId).toList();
    }

    public boolean isOperatorOccupied(Long operatorId, Long timeSlotId, Long dateEpochMillis) {
        return this.appointmentOperatorTimeSlotMappingService.getByOperatorIdTimeslotIdAndDate(operatorId, timeSlotId, dateEpochMillis, Boolean.TRUE) != null;
    }

    public AppointmentOperatorTimeSlotMapping getByIdByAppointmentId(Long appointmentId) {
        return this.appointmentOperatorTimeSlotMappingService.getByAppointmentId(appointmentId);
    }

    public List<AppointmentOperatorTimeSlotMapping> getPaginatedListByOperatorIdAndDateGreaterThanEqual(Long operatorId, Long dateEpochMillis, Long page, Long limit) {
        return this.appointmentOperatorTimeSlotMappingService.getPaginatedListByOperatorIdAndDateGreaterThanEqual(operatorId, dateEpochMillis, page, limit);
    }
}
