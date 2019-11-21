package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    WorkDay findByDate(LocalDate date);

    Boolean existsByDateBetween(LocalDate startDate, LocalDate endDate);

    List<WorkDay> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<WorkDay> findAllByDateBetweenAndDayNotLikeAndDayNotLike(
            LocalDate startDate, LocalDate endDate, String saturday, String sunday);
}
