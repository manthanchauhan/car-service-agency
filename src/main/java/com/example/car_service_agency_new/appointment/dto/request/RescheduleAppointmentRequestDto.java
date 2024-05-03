package com.example.car_service_agency_new.appointment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RescheduleAppointmentRequestDto {
    private Long newTimeSlotId;
    private Long newDateEpochMillis;
}
