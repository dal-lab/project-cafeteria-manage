package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class MenuServiceTests {

    MenuService menuService;

    @Mock
    MenuRepository menuRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        menuService = new MenuService(menuRepository);
    }

    @Test
    public void getMenuByMenuName() {

        String name = "제육볶음";

        Menu mockMenu = Menu.builder().id(1L).build();

        given(menuRepository.findByName(name)).willReturn(mockMenu);
        Menu foundMenu = menuService.getMenuByMenuName(name);

        assertThat(foundMenu.getId()).isEqualTo(1L);

    }

}