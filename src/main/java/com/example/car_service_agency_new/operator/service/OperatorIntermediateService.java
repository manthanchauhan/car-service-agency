package com.example.car_service_agency_new.operator.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service.AppointmentOperatorTimeSlotMappingIntermediateService;
import com.example.car_service_agency_new.operator.domain.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperatorIntermediateService {
    @Autowired
    private OperatorService operatorService;

    @Autowired
    private AppointmentOperatorTimeSlotMappingIntermediateService appointmentOperatorTimeSlotMappingIntermediateService;

    public Operator getAvailableOperator(Long timeslotId, Long dateEpochMillis) {
        List<Operator> operators = this.operatorService.getAllOperators();

        List<Long> occupiedOperatorIds = this.appointmentOperatorTimeSlotMappingIntermediateService.getOccupiedOperatorIds(dateEpochMillis, timeslotId);

        for (Operator operator : operators) {
            if (!occupiedOperatorIds.contains(operator.getId())) {
                return operator;
            }
        }

        return null;
    }
}
