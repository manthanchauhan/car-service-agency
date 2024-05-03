package com.example.car_service_agency_new.timeslot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeslotIntermediateService {
    @Autowired
    private TimeslotService timeslotService;

    public void getTimeslotById(Long timeslotId) {
        this.timeslotService.getTimeslotById(timeslotId);
    }
}
