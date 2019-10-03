package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class WorkDayService {

    WorkDayRepository workDayRepository;

    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository) {
        this.workDayRepository = workDayRepository;
    }

    public WorkDay getWorkDayByString(String workDay) {

        LocalDate date = stringDateToLocalDate(workDay);

        return workDayRepository.findByDate(date);
    }

    protected LocalDate stringDateToLocalDate(String workDay) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(workDay, dateTimeFormatter);

    }

}
