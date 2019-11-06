package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    WorkDay findByDate(LocalDate date);

    @Query("select count(w) from WorkDay w where w.date between ?1 and ?2")
    Integer storedMonthSize(LocalDate startDate, LocalDate endDate);
}
