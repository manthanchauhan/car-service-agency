package com.example.car_service_agency_new.appointment.dto;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentWithTimeAndOperatorDto {
    private Appointment appointment;
    private Long dateEpochMillis;
    private Long timeSlotId;
    private Long operatorId;
}
