package com.example.car_service_agency_new.appointment.service;

import com.example.car_service_agency_new.appointment.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
