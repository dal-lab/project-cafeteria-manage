package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WorkDayRepository extends CrudRepository<WorkDay, Long> {

    WorkDay findByDate(LocalDate date);

}
