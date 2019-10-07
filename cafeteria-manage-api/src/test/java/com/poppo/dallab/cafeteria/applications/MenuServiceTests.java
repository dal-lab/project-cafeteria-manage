package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.MenuPlanRepository;
import com.poppo.dallab.cafeteria.domain.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class MenuServiceTests {

    MenuService menuService;

    @Mock
    MenuRepository menuRepository;

    @Mock
    MenuPlanRepository menuPlanRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        menuService = new MenuService(menuRepository, menuPlanRepository);
    }

    @Test
    public void getMenuByMenuName() {

        String name = "제육볶음";

        Menu mockMenu = Menu.builder().id(1L).build();

        given(menuRepository.findByName(name)).willReturn(mockMenu);
        Menu foundMenu = menuService.getMenuByMenuName(name);

        assertThat(foundMenu.getId()).isEqualTo(1L);

    }

    @Test
    public void getMenusByWorkDayId() {

        List<MenuPlan> mockMenuPlans = new ArrayList<>();
        mockMenuPlans.add(MenuPlan.builder().menuId(1L).build());
        mockMenuPlans.add(MenuPlan.builder().menuId(2L).build());
        given(menuPlanRepository.findAllByWorkDayId(1L)).willReturn(mockMenuPlans);

        given(menuRepository.findById(1L))
                .willReturn(java.util.Optional.ofNullable(Menu.builder().name("제육볶음").build()));
        given(menuRepository.findById(2L))
                .willReturn(java.util.Optional.ofNullable(Menu.builder().name("볶음밥").build()));

        List<Menu> menus = menuService.getMenusByWorkDayId(1L);

        assertThat(menus.get(0).getName()).isEqualTo("제육볶음");
        assertThat(menus.get(1).getName()).isEqualTo("볶음밥");

    }

}