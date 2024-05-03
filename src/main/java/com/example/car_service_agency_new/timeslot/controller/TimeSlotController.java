package com.example.car_service_agency_new.timeslot.controller;

import com.example.car_service_agency_new.timeslot.service.TimeslotIntermediateService;
import com.example.car_service_agency_new.util.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/timeslot")
public class TimeSlotController extends AbstractBaseController {
    @Autowired
    private TimeslotIntermediateService timeslotIntermediateService;

    @GetMapping("/available")
    public ResponseEntity<List<Map<String, Long>>> getTimeslots(
            @RequestParam Long operatorId,
            @RequestParam(defaultValue = "") Long dateEpochMillis
    ) {
        return this.makeResponse(this.timeslotIntermediateService.getAvailableTimeSlots(operatorId, dateEpochMillis));
    }
}
