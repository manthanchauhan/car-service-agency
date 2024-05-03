package com.example.car_service_agency_new.appointment.service;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.domain.AppointmentOperatorTimeSlotMapping;
import com.example.car_service_agency_new.appointmentOperatorTimeslotMapping.service.AppointmentOperatorTimeSlotMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentOperatorTimeSlotMappingRepository appointmentOperatorTimeSlotMappingRepository;

    @Transactional
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

        this.appointmentOperatorTimeSlotMappingRepository.save(mapping);

        return appointment;
    }

    @Transactional(readOnly = true)
    public Appointment getById(Long id) {
        return this.appointmentRepository.getReferenceById(id);
    }

    @Transactional
    public Appointment updateAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }
}
