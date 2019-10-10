package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import com.poppo.dallab.cafeteria.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<WorkDay> getWorkWeek(LocalDate date) {

        List<LocalDate> thisWeek = dateTimeUtils.getWeekOfDate(date);

        List<WorkDay> workDays = new ArrayList<>();
        for (LocalDate weekDay : thisWeek) {
            workDays.add(workDayRepository.findByDate(weekDay));
        }
//        thisWeek.forEach(weekDay -> workDays.add(workDayRepository.findByDate(weekDay)));

        return workDays;
    }
}
