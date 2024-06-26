package com.example.car_service_agency_new.operator.service;

import com.example.car_service_agency_new.operator.domain.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return this.operatorRepository.findAll();
    }
}
