package com.example.car_service_agency_new.appointment.controller;

import com.example.car_service_agency_new.appointment.dto.AppointmentWithTimeAndOperatorDto;
import com.example.car_service_agency_new.appointment.dto.request.CreateAppointmentRequestDto;
import com.example.car_service_agency_new.appointment.dto.request.RescheduleAppointmentRequestDto;
import com.example.car_service_agency_new.appointment.dto.response.CreateAppointmentResponseDto;
import com.example.car_service_agency_new.appointment.service.AppointmentIntermediateService;
import com.example.car_service_agency_new.util.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

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

    @GetMapping("")
    public ResponseEntity<List<AppointmentWithTimeAndOperatorDto>> getAppointments(
            @RequestParam Long operatorId, // get this from token
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long limit
    ) {
        return this.makeResponse(this.appointmentIntermediateService.getAppointments(operatorId, page, limit));
    }

    @PatchMapping("/{uuid}/cancel")
    public ResponseEntity<Map<String, String>> cancelAppointment(
            @PathVariable String uuid
    ) {
        Long userId = 1L; // get this from the token
        return this.makeResponse(this.appointmentIntermediateService.cancelAppointment(uuid, userId));
    }

    @PatchMapping("/{uuid}/reschedule")
    public ResponseEntity<CreateAppointmentResponseDto> rescheduleAppointment(
            @PathVariable String uuid,
            @RequestBody RescheduleAppointmentRequestDto rescheduleAppointmentRequestDto
            ) {
        Long userId = 1L; // get this from the token

        return this.makeResponse(this.appointmentIntermediateService.rescheduleAppointment(uuid, userId, rescheduleAppointmentRequestDto));
    }
}
