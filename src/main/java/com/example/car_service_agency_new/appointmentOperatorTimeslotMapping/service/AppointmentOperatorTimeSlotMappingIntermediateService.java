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
        return this.appointmentOperatorTimeSlotMappingService.getByOperatorIdTimeslotIdAndDate(operatorId, timeslotId, dateEpochMillis);
    }

    public List<Long> getOccupiedOperatorIds(Long dateEpochMillis, Long timeSlotId) {
        List<AppointmentOperatorTimeSlotMapping> mappingList = this.appointmentOperatorTimeSlotMappingService.getByDateAndTimeSlotId(dateEpochMillis, timeSlotId);
        return mappingList.stream().map(AppointmentOperatorTimeSlotMapping::getOperatorId).toList();
    }
}
