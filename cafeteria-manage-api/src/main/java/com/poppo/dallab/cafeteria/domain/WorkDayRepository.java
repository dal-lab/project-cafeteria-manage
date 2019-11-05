package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkDayRepository extends CrudRepository<WorkDay, Long> {

    WorkDay findByDate(LocalDate date);

    List<WorkDay> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
