package com.example.car_service_agency_new.appointment.service;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import com.example.car_service_agency_new.appointment.dto.request.CreateAppointmentRequestDto;
import com.example.car_service_agency_new.appointment.dto.request.RescheduleAppointmentRequestDto;
import com.example.car_service_agency_new.appointment.dto.response.CreateAppointmentResponseDto;
import com.example.car_service_agency_new.appointment.enums.AppointmentStatus;
import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service.AppointmentOperatorTimeSlotMappingIntermediateService;
import com.example.car_service_agency_new.operator.domain.Operator;
import com.example.car_service_agency_new.operator.service.OperatorIntermediateService;
import com.example.car_service_agency_new.timeslot.service.TimeslotIntermediateService;
import com.example.car_service_agency_new.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AppointmentIntermediateService {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private OperatorIntermediateService operatorIntermediateService;

    @Autowired
    private TimeslotIntermediateService timeslotIntermediateService;

    @Autowired
    private AppointmentOperatorTimeSlotMappingIntermediateService appointmentOperatorTimeSlotMappingIntermediateService;

    public CreateAppointmentResponseDto createAppointment(Long userId, CreateAppointmentRequestDto createAppointmentRequestDto) {
        this.validateCreateAppointmentRequest(createAppointmentRequestDto);

        if (createAppointmentRequestDto.getOperatorId() == null) {
            Operator operator = this.operatorIntermediateService.getAvailableOperator(createAppointmentRequestDto.getTimeSlotId(), createAppointmentRequestDto.getDateEpochMillis());
            createAppointmentRequestDto.setOperatorId(operator.getId());
        }

        Appointment appointment = this.createAppointmentInDb(userId, createAppointmentRequestDto);

        // create order for the appointment and capture payment
        // webhook will be sent from order service to this service {"orderId": 12, "appointment_id": 1, "status": "PAID"}

        Long orderId = appointment.getId(); // get this from order service webhook

        appointment = this.markAppointmentAsBooked(appointment.getId(), orderId);

        // send notifications to the user and the operator

        return new CreateAppointmentResponseDto(appointment);
    }

    public Map<String , String> cancelAppointment(String appointmentUuid, Long userId) {
        this.validateCancelAppointmentRequest(appointmentUuid, userId);

        this.appointmentService.cancelAppointment(appointmentUuid);
        // trigger refund for the order

        return new HashMap<>(){{
            put("message", "Appointment cancelled successfully");
        }};
    }

    public CreateAppointmentResponseDto rescheduleAppointment(String appointmentUuid, Long userId, RescheduleAppointmentRequestDto rescheduleAppointmentRequestDto) {
        // Assumption: different hours have different pricing

        this.validateRescheduleAppointmentRequest(rescheduleAppointmentRequestDto);

        Appointment appointment = this.appointmentService.getByUuid(appointmentUuid);
        Long operatorId = this.appointmentOperatorTimeSlotMappingIntermediateService.getByIdByAppointmentId(appointment.getId()).getOperatorId();

        boolean isOperatorOccupied = this.appointmentOperatorTimeSlotMappingIntermediateService.isOperatorOccupied(
                operatorId,
                rescheduleAppointmentRequestDto.getNewTimeSlotId(),
                rescheduleAppointmentRequestDto.getNewDateEpochMillis()
        );

        if (isOperatorOccupied) {
            throw new RuntimeException("Operator is not available at the given time slot");
        }

        this.cancelAppointment(appointmentUuid, userId);

        CreateAppointmentRequestDto createAppointmentRequestDto = new CreateAppointmentRequestDto(
                operatorId,
                rescheduleAppointmentRequestDto.getNewTimeSlotId(),
                appointment.getVehicleModelId(),
                appointment.getNotes(),
                rescheduleAppointmentRequestDto.getNewDateEpochMillis()
        );

        return this.createAppointment(userId, createAppointmentRequestDto);
    }

    private void validateCancelAppointmentRequest(String appointmentUuid, Long userId) {
        Appointment appointment = this.appointmentService.getByUuid(appointmentUuid);

        if (!Objects.equals(appointment.getUserId(), userId)) {
            throw new RuntimeException("User is not authorized to cancel the appointment");
        }

        if (appointment.getStatus().equals(AppointmentStatus.IN_PROGRESS.name()) ||
                appointment.getStatus().equals(AppointmentStatus.COMPLETED.name()) ||
                appointment.getStatus().equals(AppointmentStatus.CANCELLED.name())
        ) {
            throw new RuntimeException("Appointment cannot be cancelled");
        }
    }

    private void validateCreateAppointmentRequest(CreateAppointmentRequestDto createAppointmentRequestDto) {
        this.timeslotIntermediateService.getTimeslotById(createAppointmentRequestDto.getTimeSlotId());
        this.validateDateIsInFuture(createAppointmentRequestDto.getDateEpochMillis());

        if (createAppointmentRequestDto.getOperatorId() != null) {
            boolean isOperatorOccupied = this.appointmentOperatorTimeSlotMappingIntermediateService.isOperatorOccupied(
                    createAppointmentRequestDto.getOperatorId(),
                    createAppointmentRequestDto.getTimeSlotId(),
                    createAppointmentRequestDto.getDateEpochMillis()
            );

            if (isOperatorOccupied) {
                throw new RuntimeException("Operator is not available at the given time slot");
            }
        }
    }

    private void validateRescheduleAppointmentRequest(RescheduleAppointmentRequestDto rescheduleAppointmentRequestDto) {
        this.timeslotIntermediateService.getTimeslotById(rescheduleAppointmentRequestDto.getNewTimeSlotId());
        this.validateDateIsInFuture(rescheduleAppointmentRequestDto.getNewDateEpochMillis());
    }

    private void validateDateIsInFuture(Long dateEpochMillis) {
        if (dateEpochMillis < Util.getStartTimeStampOfDay(new Date())) {
            throw new RuntimeException("Date should be in the future");
        }
    }

    private Appointment createAppointmentInDb(Long userId, CreateAppointmentRequestDto createAppointmentRequestDto) {
        Appointment appointment = new Appointment(
            userId,
            createAppointmentRequestDto.getVehicleModelId(),
            AppointmentStatus.PENDING.name(),
            createAppointmentRequestDto.getNotes()
        );

        return this.appointmentService.createAppointment(
                appointment,
                createAppointmentRequestDto.getOperatorId(),
                createAppointmentRequestDto.getTimeSlotId(),
                createAppointmentRequestDto.getDateEpochMillis()
        );
    }

    private Appointment markAppointmentAsBooked(Long appointmentId, Long orderId) {
        Appointment appointment = this.appointmentService.getById(appointmentId);

        appointment.setOrderId(orderId);
        appointment.setStatus(AppointmentStatus.BOOKED.name());

        return this.appointmentService.updateAppointment(appointment);
    }
}
