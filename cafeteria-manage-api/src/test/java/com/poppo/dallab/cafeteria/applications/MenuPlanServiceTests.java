package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.MenuPlanRepository;
import com.poppo.dallab.cafeteria.domain.MenuRepository;
import com.poppo.dallab.cafeteria.exceptions.NoMenuException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MenuPlanServiceTests {

    private MenuPlanService menuPlanService;

    @MockBean
    private MenuRepository menuRepository;

    @MockBean
    private MenuPlanRepository menuPlanRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        menuPlanService = new MenuPlanService(menuRepository, menuPlanRepository);
    }

    @Test
    public void 요청된_WorkDay_Menu_모두_존재할_때_menu추가_요청_처리하기() {

        Menu menu = Menu.builder().id(3L).build();
        given(menuRepository.findByName("닭갈비")).willReturn(Optional.ofNullable(menu));

        MenuPlan menuPlan = MenuPlan.builder().id(1L).build();
        given(menuPlanRepository.save(any(MenuPlan.class))).willReturn(menuPlan);

        MenuPlan saved = menuPlanService.addMenu(1L, "닭갈비");

        assertThat(saved.getId()).isEqualTo(1L);
    }

    @Test(expected = NoMenuException.class)
    public void 요청된__Menu__존재하지_않을_때_menu추가_요청_처리하기() {

        given(menuRepository.findByName("이제까지이런맛은없었다이것은갈비인가치킨인가")).willThrow(NoMenuException.class);

        MenuPlan saved = menuPlanService.addMenu(1L, "이제까지이런맛은없었다이것은갈비인가치킨인가");
    }

}