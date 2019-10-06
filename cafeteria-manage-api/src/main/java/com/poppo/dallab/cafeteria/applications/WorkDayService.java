package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import com.poppo.dallab.cafeteria.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkDayService {

    private final WorkDayRepository workDayRepository;
    private final DateTimeUtils dateTimeUtils;

    public WorkDay getWorkDayByString(String workDay) {

        LocalDate date = dateTimeUtils.stringDateToLocalDate(workDay);

        return workDayRepository.findByDate(date);
    }
}
