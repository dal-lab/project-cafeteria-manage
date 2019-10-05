package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import com.poppo.dallab.cafeteria.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkDayService {

    WorkDayRepository workDayRepository;

    DateTimeUtils dateTimeUtils;

    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository, DateTimeUtils dateTimeUtils) {
        this.workDayRepository = workDayRepository;
        this.dateTimeUtils = dateTimeUtils;
    }

    public WorkDay getWorkDayByString(String workDay) {

        LocalDate date = dateTimeUtils.stringDateToLocalDate(workDay);

        return workDayRepository.findByDate(date);
    }

}
