package com.example.car_service_agency_new.timeslot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeslotService {
    @Autowired
    private TimeslotRepository timeslotRepository;

    public void getTimeslotById(Long timeslotId) {
        this.timeslotRepository.findById(timeslotId).orElseThrow(() -> new RuntimeException("Timeslot not found"));
    }
}
