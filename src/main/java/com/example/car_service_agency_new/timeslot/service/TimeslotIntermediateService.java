package com.example.car_service_agency_new.timeslot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car_service_agency_new.timeslot.domain.Timeslot;

@Service
public class TimeslotIntermediateService {
    @Autowired
    private TimeslotService timeslotService;

    public Timeslot getTimeslotById(Long timeslotId) {
        return this.timeslotService.getTimeslotById(timeslotId);
    }
}
