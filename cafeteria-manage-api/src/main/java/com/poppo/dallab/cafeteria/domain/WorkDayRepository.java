package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    WorkDay findByDate(LocalDate date);

    Boolean existsByDateBetween(LocalDate startDate, LocalDate endDate);
}
