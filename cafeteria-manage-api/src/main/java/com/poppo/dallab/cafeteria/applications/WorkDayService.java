package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import com.poppo.dallab.cafeteria.exceptions.WeekCountExceedException;
import com.poppo.dallab.cafeteria.utils.DateTimeUtils;
import com.poppo.dallab.cafeteria.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
    private final PageUtils pageUtils;

    public WorkDay getWorkDayByString(String workDay) {

        LocalDate date = dateTimeUtils.stringDateToLocalDate(workDay);

        return workDayRepository.findByDate(date);
    }

    public List<WorkDay> getWorkWeek(LocalDate date) {

        List<LocalDate> thisWeek = dateTimeUtils.getWeekOfDateExceptWeekend(date);

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
            if(this.isThisMonthExists(month)) {
                workMonths.add(month);
            }
        });

        return workMonths;
    }

    protected Boolean isThisMonthExists(Integer month) {

        Integer thisYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(thisYear, month, 1);
        LocalDate endDate = LocalDate.of(thisYear, month, dateTimeUtils.getDayLengthOfMonth(thisYear, month));

        return workDayRepository.existsByDateBetween(startDate,endDate);
    }

    public WorkDay getWorkDayById(Long id) {

        return workDayRepository.getOne(id);
    }

    public List<WorkDay> getWorkDaysByMonth(Integer year, Integer month, Integer weekCount) {

        if (weekCount == 1) {
            return this.getFirstWeekWorkDay(year, month);
        }

        List<LocalDate> mondaysOfMonth = dateTimeUtils.getMondaysOfMonthExceptFirstWeek(year, month);

        // 해당 월 내의 주에 대해서만 처리
        // 첫주가 무조건 제외되기 때문에 필
        if (weekCount > mondaysOfMonth.size() + 1) throw new WeekCountExceedException();

        // 무조건 2주차부터 오기 때문에 숫자 계산이 이상해짐
        LocalDate startDate = mondaysOfMonth.get(weekCount - 2);
        List<WorkDay> workDays = workDayRepository.findAllByDateBetweenAndDayNotLikeAndDayNotLike(
                startDate, startDate.plusDays(4L), "SATURDAY", "SUNDAY");

        return workDays;
    }

    protected List<WorkDay> getFirstWeekWorkDay(Integer year, Integer month) {

        LocalDate firstDate = LocalDate.of(year,month,1);

        List<LocalDate> firstWeekDays
                = dateTimeUtils.getWeekOfDateExceptWeekend(firstDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)))
                .stream()
                .filter(firstWeekDay -> firstWeekDay.getMonthValue() == month)
                .collect(Collectors.toList());

        List<WorkDay> firstWeekWorkDays = workDayRepository
                .findAllByDateBetweenAndDayNotLikeAndDayNotLike(
                        firstWeekDays.get(0),
                        firstWeekDays.get(firstWeekDays.size() - 1),
                        "SATURDAY",
                        "SUNDAY"
                );

        return firstWeekWorkDays;
    }
}
