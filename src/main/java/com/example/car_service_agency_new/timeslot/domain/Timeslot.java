package com.example.car_service_agency_new.timeslot.domain;

import com.example.car_service_agency_new.util.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time_slots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Timeslot extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_hour")
    private Long startHour;

    @Column(name = "end_hour")
    private Long endHour;
}
