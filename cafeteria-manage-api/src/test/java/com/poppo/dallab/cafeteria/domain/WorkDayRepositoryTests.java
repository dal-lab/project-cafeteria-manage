package com.poppo.dallab.cafeteria.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class WorkDayRepositoryTests {

    @Autowired
    private WorkDayRepository workDayRepository;

    @Test
    public void 저장된_workDay_중_제시된_범위에_속하는_게_있는가() {

        WorkDay workDay = WorkDay.builder()
                .date(LocalDate.of(2019,10,1))
                .build();
        workDayRepository.save(workDay);

        LocalDate startDate = LocalDate.of(2019, 10, 1);
        LocalDate endDate = LocalDate.of(2019, 10, 30);

        Boolean result = workDayRepository.existsByDateBetween(startDate, endDate);

        assertThat(result).isTrue();
    }

    @Test
    public void 저장된_workDay_중_제시된_범위_내에_속하는_결과를_가져오고_주말을_제외했는가() {

        IntStream.rangeClosed(1, 30).forEach(i -> {

            LocalDate date = LocalDate.of(2019, 11, i);

            WorkDay workDay = WorkDay.builder()
                    .day(date.getDayOfWeek().name())
                    .date(date)
                    .build();

            workDayRepository.save(workDay);
        });

        LocalDate startDate = LocalDate.of(2019, 11, 1);
        LocalDate endDate = LocalDate.of(2019, 11, 30);

        PageRequest pageable = PageRequest.of(0, 30);

        List<WorkDay> weekendFilteredWorkDay = workDayRepository.findAllByDateBetweenAndDayNotLikeAndDayNotLike(
                startDate, endDate, "SATURDAY", "SUNDAY", pageable);

        assertThat(weekendFilteredWorkDay).hasSize(21);
    }

}
