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
import java.util.stream.IntStream;

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

        List<WorkDay> workDays = daysOfMonth.stream()
                .map(dayOfMonth -> WorkDay.builder()
                        .date(dayOfMonth)
                        .day(dayOfMonth.getDayOfWeek().name())
                        .build())
                .collect(Collectors.toList());

        workDayRepository.saveAll(workDays);

        return workDays;
    }

    public List<Integer> getWorkMonths() {

        List<Integer> workMonths = new ArrayList<>();

        IntStream.rangeClosed(1,12).forEach(month -> {
            if(this.getSavedMonthSize(month) != 0) {
                workMonths.add(month);
            }
        });

        return workMonths;
    }

    protected Integer getSavedMonthSize(Integer month) {

        Integer thisYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(thisYear, month, 1);
        LocalDate endDate = LocalDate.of(thisYear, month, dateTimeUtils.getDayLengthOfMonth(thisYear, month));

        return workDayRepository.storedMonthSize(startDate, endDate);
    }
}
