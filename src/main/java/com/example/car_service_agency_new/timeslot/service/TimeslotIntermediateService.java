package com.example.car_service_agency_new.timeslot.service;

import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service.AppointmentOperatorTimeSlotMappingIntermediateService;
import com.example.car_service_agency_new.timeslot.domain.Timeslot;
import com.example.car_service_agency_new.util.Util;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TimeslotIntermediateService {
    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private AppointmentOperatorTimeSlotMappingIntermediateService appointmentOperatorTimeSlotMappingIntermediateService;

    public void getTimeslotById(Long timeslotId) {
        this.timeslotService.getTimeslotById(timeslotId);
    }

    public List<Map<String, Long>> getAvailableTimeSlots(Long operatorId, Long dateEpochMillis) {
        dateEpochMillis = Util.getStartTimeStampOfDay(dateEpochMillis == null ? new Date() : new Date(dateEpochMillis));

        List<Long> occupiedTimeSlots = this.appointmentOperatorTimeSlotMappingIntermediateService.getOccupiedTimeSlotIds(dateEpochMillis, operatorId);

        List<Timeslot> allTimeSlots = this.timeslotService.getAllTimeSlots();

        List<Timeslot> availableTimeSlots = allTimeSlots.stream()
                .filter(timeslot -> !occupiedTimeSlots.contains(timeslot.getId()))
                .collect(Collectors.toList())
                ;

        List<Pair<Long, Long>> mergedTimeSlots = this.getMergedTimeSlots(availableTimeSlots);

        return mergedTimeSlots.stream()
                .map(pair -> Map.of("startHour", pair.a, "endHour", pair.b))
                .collect(Collectors.toList())
                ;
    }

    public List<Pair<Long, Long>> getMergedTimeSlots(List<Timeslot> timeslots) {
        timeslots.sort(Comparator.comparing(Timeslot::getStartHour));

        List<Pair<Long, Long>> resultList = new ArrayList<>();

        Timeslot aggregatedTimeSlot = timeslots.get(0);

        for (Timeslot currentTimeSlot : timeslots.subList(1, timeslots.size())) {
            if (aggregatedTimeSlot.getEndHour() >= currentTimeSlot.getStartHour()) {
                aggregatedTimeSlot.setEndHour(currentTimeSlot.getEndHour());
            } else {
                resultList.add(new Pair<>(aggregatedTimeSlot.getStartHour(), aggregatedTimeSlot.getEndHour()));
                aggregatedTimeSlot = currentTimeSlot;
            }
        }

        resultList.add(new Pair<>(aggregatedTimeSlot.getStartHour(), aggregatedTimeSlot.getEndHour()));
        return resultList;
    }
}
