package com.example.car_service_agency_new.appointment.service;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> getByUuid(String uuid);

    List<Appointment> findByIdIn(List<Long> appointmentIdList);
}
