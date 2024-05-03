package com.example.car_service_agency_new.appointment.service;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import com.example.car_service_agency_new.appointment.enums.AppointmentStatus;
import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service.AppointmentOperatorTimeSlotMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentOperatorTimeSlotMappingRepository appointmentOperatorTimeSlotMappingRepository;

    public Appointment createAppointment(Appointment appointment, Long operatorId, Long timeSlotId, Long dateEpochMillis) {
        this.appointmentRepository.save(appointment);

        // unique constraint on operator, timeslot, date, is_active to prevent over booking
        AppointmentOperatorTimeSlotMapping mapping = new AppointmentOperatorTimeSlotMapping(
                null,
            appointment.getId(),
            operatorId,
            timeSlotId,
            dateEpochMillis,
            Boolean.TRUE
        );

        // deactivate the mapping if payment is not captured within 15 mins, via cron, redis scheduler etc.

        this.appointmentOperatorTimeSlotMappingRepository.save(mapping);

        return appointment;
    }

    @Transactional
    public void cancelAppointment(String appointmentUuid) {
        Appointment appointment = this.getByUuid(appointmentUuid);
        appointment.setStatus(AppointmentStatus.CANCELLED.name());
        this.updateAppointment(appointment);

        AppointmentOperatorTimeSlotMapping mapping = this.appointmentOperatorTimeSlotMappingRepository.findByAppointmentId(appointment.getId())
                .orElseThrow(() -> new RuntimeException("Something went wrong"));

        mapping.setIsActive(Boolean.FALSE);
        this.appointmentOperatorTimeSlotMappingRepository.save(mapping);
    }

    public Appointment getById(Long id) {
        return this.appointmentRepository.getReferenceById(id);
    }

    public Appointment getByUuid(String uuid) {
        return this.appointmentRepository.getByUuid(uuid).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment updateAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    public List<Appointment> findByIdIn(List<Long> appointmentIdList) {
        return this.appointmentRepository.findByIdIn(appointmentIdList);
    }
}
