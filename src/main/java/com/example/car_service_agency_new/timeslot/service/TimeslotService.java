package com.example.car_service_agency_new.timeslot.service;

import com.example.car_service_agency_new.timeslot.domain.Timeslot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeslotService {
    @Autowired
    private TimeslotRepository timeslotRepository;

    public Timeslot getTimeslotById(Long timeslotId) {
        return this.timeslotRepository.findById(timeslotId).orElseThrow(() -> new RuntimeException("Timeslot not found"));
    }
}
