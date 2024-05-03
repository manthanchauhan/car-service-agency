package com.example.car_service_agency_new.operator.service;

import com.example.car_service_agency_new.operator.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
