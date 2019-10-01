package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MenuPlanServiceTests {

    MenuPlanService menuPlanService;

    @MockBean
    MenuPlanRepository menuPlanRepository;

    @MockBean
    WorkDayRepository workDayRepository;

    @MockBean
    MenuRepository menuRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        menuPlanService = new MenuPlanService(menuPlanRepository, workDayRepository, menuRepository);
    }

    // TODO: 의존성이 너무 덕지덕지 붙고 있음 리팩토링 필요
    @Test
    public void addMenuPlanWithMenuAndWorkDay() {

        String workDay = "2019-10-01";

        Menu mockMenu = Menu.builder()
                .id(2L)
                .name("제육볶음")
                .build();

        MenuPlan mockMenuPlan = MenuPlan.builder()
                .id(3L)
                .menuId(2L)
                .workDayId(1L)
                .build();

        // MenuPlanRepository 의존성
        given(menuPlanRepository.save(any())).willReturn(mockMenuPlan);

        // WorkDayRepository 의존성
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(workDay, dateTimeFormatter);
        WorkDay mockWorkDay = WorkDay.builder().id(3L).build();
        given(workDayRepository.findByDate(date)).willReturn(mockWorkDay);

        // MenuRepository 의존성
        given(menuRepository.findByName("제육볶음")).willReturn(mockMenu);

        MenuPlan menuPlan = menuPlanService.addMenu(workDay, mockMenu);
        assertThat(menuPlan.getId()).isEqualTo(3L);
        assertThat(menuPlan.getMenuId()).isEqualTo(2L);
        assertThat(menuPlan.getWorkDayId()).isEqualTo(1L);

        // TODO: 정확한 타입이 들어가는지 확인하는 로직 만들 것
        verify(menuPlanRepository).save(any());

    }

    @Test
    public void getWorkDayId() {

        String workDay = "2019-10-01";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(workDay, dateTimeFormatter);

        WorkDay mockWorkDay = WorkDay.builder().id(3L).build();

        given(workDayRepository.findByDate(date)).willReturn(mockWorkDay);

        Long workDayId = menuPlanService.getWorkDayId(workDay);

        assertThat(workDayId).isEqualTo(3L);

    }

}