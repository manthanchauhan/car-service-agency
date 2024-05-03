package com.example.car_service_agency_new.appointment.dto.response;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentResponseDto {
    private Long appointmentId;
    private Long userId;
    private Long vehicleModelId;
    private String status;
    private Long orderId;
    private String notes;

    public CreateAppointmentResponseDto(Appointment appointment) {
        this.appointmentId = appointment.getId();
        this.userId = appointment.getUserId();
        this.vehicleModelId = appointment.getVehicleModelId();
        this.status = appointment.getStatus();
        this.orderId = appointment.getOrderId();
        this.notes = appointment.getNotes();
    }
}
