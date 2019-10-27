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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

    @Test
    public void 메뉴_리스트_조회() {
        List<Menu> mockMenus = Arrays.asList(Menu.builder().name("제육볶음").build());
        given(menuRepository.findAll()).willReturn(mockMenus);

        List<Menu> menus = menuService.getMenus();

        assertThat(menus.get(0).getName()).isEqualTo("제육볶음");
    }

    @Test
    public void 존재하는_메뉴_하나만_가져오기() {

        Menu mockMenu = Menu.builder()
                .name("제육볶음")
                .id(1L)
                .build();

        given(menuRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(mockMenu));

        Menu menu = menuService.getMenuById(1L);

        assertThat(menu.getId()).isEqualTo(1L);
        assertThat(menu.getName()).isEqualTo("제육볶음");
    }

    @Test(expected = MenuNotFoundException.class)
    public void 없는_메뉴_하나만_가져오기() {

        menuService.getMenuById(44L);

    }

    @Test
    public void 새_메뉴_저장() {
        Menu mockMenu = Menu.builder().id(1L).name("제육볶음").build();

        given(menuRepository.save(any())).willReturn(mockMenu);

        Menu saved = menuService.addMenu("제육볶음");

        assertThat(saved.getId()).isEqualTo(1L);
        assertThat(saved.getName()).isEqualTo("제육볶음");
    }

    @Test
    public void 메뉴_삭제() {

        menuService.removeMenu(1L);

        verify(menuRepository).deleteById(eq(1L));

    }

}