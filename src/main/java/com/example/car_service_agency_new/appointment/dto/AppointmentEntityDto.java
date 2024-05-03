package com.example.car_service_agency_new.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntityDto {
    private Long operatorId;
    private Long userId;
    private Long startTime;
    private Long endTime;
    private Long vehicleModelId;
    private String status;
    private Long orderId;
    private String notes;
}
