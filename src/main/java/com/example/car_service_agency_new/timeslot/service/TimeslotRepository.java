package com.example.car_service_agency_new.timeslot.service;

import com.example.car_service_agency_new.timeslot.domain.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
}
