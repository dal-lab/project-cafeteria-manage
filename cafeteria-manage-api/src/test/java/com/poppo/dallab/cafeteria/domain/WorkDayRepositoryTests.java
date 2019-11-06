package com.poppo.dallab.cafeteria.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class WorkDayRepositoryTests {

    @Autowired
    private WorkDayRepository workDayRepository;

    @Test
    public void 저장된_workDay_중_제시된_범위에_속하는_건_몇개일까() {
        WorkDay workDay = WorkDay.builder()
                .date(LocalDate.of(2019,10,1))
                .build();
        workDayRepository.save(workDay);

        LocalDate startDate = LocalDate.of(2019, 10, 1);
        LocalDate endDate = LocalDate.of(2019, 10, 30);

        Integer monthSize = workDayRepository.storedMonthSize(startDate, endDate);

        assertThat(monthSize).isEqualTo(1);
    }

}
