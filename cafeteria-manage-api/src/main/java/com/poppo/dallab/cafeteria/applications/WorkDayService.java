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
import java.util.stream.Collectors;

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

    public List<WorkDay> bulkCreate(Integer month) {

        // TODO: LocalDate 여러개 생성하는 걸 DateTimeUtil이 할지 WorkDay Domain이 할지 고민해볼 것
        // TODO: 아니 그 전에 이렇게 한방에 빡 하고 만드는 게 옳은일인지부터 고민해봐야 되지 않을까...

        List<LocalDate> daysOfMonth = dateTimeUtils.getLocalDatesByMonth(month);

        return daysOfMonth.stream().map(dayOfMonth ->
                    workDayRepository.save(WorkDay.builder()
                        .date(dayOfMonth)
                        .day(dayOfMonth.getDayOfWeek().name())
                        .build()))
                .collect(Collectors.toList());
    }
}
