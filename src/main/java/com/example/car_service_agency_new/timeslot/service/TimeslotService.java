package com.example.car_service_agency_new.timeslot.service;

import com.example.car_service_agency_new.timeslot.domain.Timeslot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimeslotService {
    @Autowired
    private TimeslotRepository timeslotRepository;

    public void getTimeslotById(Long timeslotId) {
        this.timeslotRepository.findById(timeslotId).orElseThrow(() -> new RuntimeException("Timeslot not found"));
    }

    public List<Timeslot> getAllTimeSlots() {
        return this.timeslotRepository.findAll();
    }
}
