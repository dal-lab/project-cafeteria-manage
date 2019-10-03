package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class MenuPlanServiceTests {

    MenuPlanService menuPlanService;

    @MockBean
    MenuPlanRepository menuPlanRepository;

    @Mock
    WorkDayService workDayService;

    @MockBean
    MenuService menuService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        menuPlanService = new MenuPlanService(menuPlanRepository, workDayService, menuService);
    }

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
        WorkDay mockWorkDay = WorkDay.builder().id(3L).build();
        given(workDayService.getWorkDayByString(workDay)).willReturn(mockWorkDay);

        // MenuRepository 의존성
        Menu mockReturnMenu = Menu.builder().id(2L).build();
        given(menuService.getMenuByMenuName("제육볶음")).willReturn(mockReturnMenu);

        MenuPlan menuPlan = menuPlanService.addMenu(workDay, mockMenu.getName());
        assertThat(menuPlan.getId()).isEqualTo(3L);
        assertThat(menuPlan.getMenuId()).isEqualTo(2L);
        assertThat(menuPlan.getWorkDayId()).isEqualTo(1L);

    }

}