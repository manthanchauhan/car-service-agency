package com.example.car_service_agency_new.appointment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentRequestDto {
    private Long operatorId;

    @NonNull
    private Long timeSlotId;

    @NonNull
    private Long vehicleModelId;

    private String notes;

    @NonNull
    private Long dateEpochMillis;
}
