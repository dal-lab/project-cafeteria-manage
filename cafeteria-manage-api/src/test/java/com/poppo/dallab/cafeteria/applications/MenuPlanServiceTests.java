package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import com.poppo.dallab.cafeteria.exceptions.MenuNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.MenuPlanNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.WorkDayNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
public class MenuPlanServiceTests {

    private MenuPlanService menuPlanService;
    private Long workDayId;
    private Long menuId;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuPlanRepository menuPlanRepository;

    @Mock
    private WorkDayRepository workDayRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        workDayId = 1L;
        menuId = 3L;

        menuPlanService = new MenuPlanService(menuRepository, menuPlanRepository, workDayRepository);
    }

    @Test
    public void 요청된_WorkDay_Menu_모두_존재할_때_menu추가_요청_처리하기() {

        Menu menu = Menu.builder().id(menuId).build();
        given(menuRepository.findByName("닭갈비")).willReturn(Optional.ofNullable(menu));

        MenuPlan menuPlan = MenuPlan.builder().id(1L).build();
        given(menuPlanRepository.save(any(MenuPlan.class))).willReturn(menuPlan);

        MenuPlan saved = menuPlanService.addMenu(workDayId, "닭갈비", 65535D);

        assertThat(saved.getId()).isEqualTo(1L);
    }

    @Test(expected = MenuNotFoundException.class)
    public void 요청된_Menu_존재하지_않을_때_menu추가_요청_처리하기() {

        given(menuRepository.findByName("이제까지이런맛은없었다이것은갈비인가치킨인가")).willThrow(MenuNotFoundException.class);

        menuPlanService.addMenu(workDayId, "이제까지이런맛은없었다이것은갈비인가치킨인가", 65535D);
    }

    @Test
    public void workDayId_menuPlanId_모두_존재할때_삭제하기() {

        given(workDayRepository.findById(workDayId)).willReturn(Optional.ofNullable(WorkDay.builder().build()));
        given(menuRepository.findById(menuId)).willReturn(Optional.ofNullable(Menu.builder().build()));

        menuPlanService.deleteMenuPlan(workDayId, menuId);

        verify(menuPlanRepository).deleteMenuPlansByWorkDayIdAndMenuId(workDayId, menuId);
    }

    @Test(expected = WorkDayNotFoundException.class)
    public void workDay가_없을_때_삭제하기() {

        given(workDayRepository.findById(44L)).willThrow(WorkDayNotFoundException.class);

        menuPlanService.deleteMenuPlan(44L, menuId);
    }

    @Test(expected = MenuNotFoundException.class)
    public void menu가_없을_때_삭제하기() {

        given(workDayRepository.findById(workDayId)).willReturn(Optional.ofNullable(WorkDay.builder().build()));
        given(menuRepository.findById(44L)).willThrow(MenuNotFoundException.class);

        menuPlanService.deleteMenuPlan(workDayId, 44L);
    }

    @Test
    public void menuId와_workDayId로_menuPlan_가져오기() {

        given(menuPlanRepository.findByWorkDayIdAndMenuId(workDayId, menuId)).willReturn(
                Optional.ofNullable(MenuPlan.builder().id(1L).build())
        );

        MenuPlan menuPlan = menuPlanService.getMenuPlanByWorkDayIdAndMenuId(workDayId, menuId);

        assertThat(menuPlan.getId()).isEqualTo(1L);
    }

    @Test
    public void 존재하는_MenuPlan의_pos변경하기_성공() {

        given(menuPlanRepository.findById(33L)).willReturn(Optional.ofNullable(MenuPlan.builder().build()));

        MenuPlan menuPlan = menuPlanService.updateMenuPlan(33L, workDayId, menuId, 277D);

        assertThat(menuPlan.getPos()).isEqualTo(277D);
        assertThat(menuPlan.getWorkDayId()).isEqualTo(workDayId);
    }

    @Test(expected = MenuPlanNotFoundException.class)
    public void 존재하지_않는_Menuplan_pos변경_시도시_MenuPlanNotExist_에러발생() {

        menuPlanService.updateMenuPlan(44L,4L, 4L, 444D);
    }

    @Test
    public void 존재하는_WorkDayId로_MenPlan_조회_시도_및_성공() {

        given(menuPlanRepository.findAllByWorkDayIdOrderByPos(3L))
                .willReturn(Arrays.asList(MenuPlan.builder().id(1L).build()));

        List<MenuPlan> menuPlans = menuPlanService.getMenuPlansByWorkDayId(3L);

        assertThat(menuPlans.get(0).getId()).isEqualTo(1L);
    }
}