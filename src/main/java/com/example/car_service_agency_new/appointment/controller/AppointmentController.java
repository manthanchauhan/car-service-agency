package com.example.car_service_agency_new.appointment.controller;

import com.example.car_service_agency_new.appointment.dto.request.CreateAppointmentRequestDto;
import com.example.car_service_agency_new.appointment.dto.response.CreateAppointmentResponseDto;
import com.example.car_service_agency_new.appointment.service.AppointmentIntermediateService;
import com.example.car_service_agency_new.util.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/appointment")
@RestController
public class AppointmentController extends AbstractBaseController {
    @Autowired
    private AppointmentIntermediateService appointmentIntermediateService;

    @PutMapping("")
    public ResponseEntity<CreateAppointmentResponseDto> createAppointment(
            @RequestBody CreateAppointmentRequestDto createAppointmentRequestDto
    ) {
        Long userId = 1L; // get this from the token
        return this.makeResponse(this.appointmentIntermediateService.createAppointment(userId, createAppointmentRequestDto));
    }
}
