package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.applications.WorkDayService;
import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.exceptions.MenuNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.MenuPlanNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.WorkDayNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuPlanController.class)
public class MenuPlanControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    MenuPlanService menuPlanService;

    @MockBean
    WorkDayService workDayService;

    @MockBean
    MenuService menuService;

    @Test
    public void getListByMonth() throws Exception {

        List<WorkDay> workDays = Arrays.asList(WorkDay.builder()
                .id(1L)
                .date(LocalDate.of(2019,11,1))
                .build());

        List<Menu> menus = Arrays.asList(Menu.builder()
                .id(3L)
                .name("밥")
                .build());

        MenuPlan menuPlan = MenuPlan.builder().pos(65535D).build();

        given(workDayService.getWorkDaysByMonth(2019, 11, 1)).willReturn(workDays);
        given(menuService.getMenusByWorkDayId(1L)).willReturn(menus);
        given(menuPlanService.getMenuPlanByWorkDayIdAndMenuId(1L, 3L)).willReturn(menuPlan);

        mvc.perform(get("/menuPlans?year=2019&month=11&weekCount=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")))
                .andExpect(content().string(containsString("2019-11-01")))
                .andExpect(content().string(containsString("\"workDayId\":1")))
                .andExpect(content().string(containsString("\"menus\":[")))
                .andExpect(content().string(containsString("밥")))
                .andExpect(content().string(containsString("\"pos\":65535")))
            ;
    }

    @Test
    public void workDay기준으로_menu까지_다_불러오기() throws Exception {

        WorkDay mockWorkDay = WorkDay.builder()
                .id(1L)
                .date(LocalDate.of(2019,9,30))
                .day(LocalDate.of(2019,9,30).getDayOfWeek().name())
                .build();
        given(workDayService.getWorkDayByString("2019-09-30")).willReturn(mockWorkDay);

        List<Menu> menus = new ArrayList<>();
        menus.add(Menu.builder().name("제육볶음").build());
        menus.add(Menu.builder().name("볶음밥").build());
        given(menuService.getMenusByWorkDayId(1L)).willReturn(menus);

        // when
        mvc.perform(get("/workDay/2019-09-30"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("day")))
                .andExpect(content().string(containsString("date")))
                .andExpect(content().string(containsString("menus")))
                .andExpect(content().string(containsString("2019-09-30")))
                .andExpect(content().string(containsString("MONDAY")))
                .andExpect(content().string(containsString("제육볶음")))
                .andExpect(content().string(containsString("볶음밥")))
                .andExpect(content().string(containsString("[")))
        ;

    }

    @Test
    public void 존재하는_메뉴를_해당날짜의_식단에_추가하기() throws Exception {

        MenuPlan menuPlan = MenuPlan.builder().id(1L).build();

        given(menuPlanService.addMenu(1L, "닭갈비", 65535D)).willReturn(menuPlan);

        mvc.perform(post("/workDays/1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"menuName\": \"닭갈비\",\n" +
                        "  \"pos\": 65535\n" +
                        "}"))
                .andExpect(header().stringValues("Location", "/menuPlans/1"))
                .andExpect(status().isCreated());
    }

    @Test
    public void 존재하지_않는_메뉴를_해당날짜의_식단에_추가하기() throws Exception {

        given(menuPlanService.addMenu(1L, "이제까지이런맛은없었다이것은갈비인가통닭인가", 65535D))
                .willThrow(MenuNotFoundException.class);

        mvc.perform(post("/workDays/1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"menuName\": \"이제까지이런맛은없었다이것은갈비인가통닭인가\",\n" +
                        "  \"pos\": 65535\n" +
                        "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void 선택된_일자의_선택된_존재하는_메뉴를_삭제하기() throws Exception {
        mvc.perform(delete("/workDays/1/menu/3"))
                .andExpect(status().isOk())
        ;

        verify(menuPlanService).deleteMenuPlan(1L, 3L);
    }

    @Test
    public void 선택된_일자의_선택된_존재하지_않는_메뉴_삭제하기() throws Exception {

        given(menuPlanService.deleteMenuPlan(1L, 44L))
                .willThrow(new MenuNotFoundException());

        mvc.perform(delete("/workDays/1/menu/44"))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void 이상한_날짜에_삭제_시도하기() throws Exception {

        given(menuPlanService.deleteMenuPlan(44L, 1L))
                .willThrow(new WorkDayNotFoundException());

        mvc.perform(delete("/workDays/44/menu/1"))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void 존재하는_menuPlan의_pos변경_성공() throws Exception {

        given(menuPlanService.updateMenuPlan(1L, 3L, 233D))
                .willReturn(MenuPlan.builder().id(1L).build());

        mvc.perform(patch("/workDays/1/menus/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"pos\": 233\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/menuPlan/1")))
        ;
    }

    @Test
    public void 존재하지_않는_menuPlan의_pos변경_실패_404() throws Exception {

        given(menuPlanService.updateMenuPlan(4L, 4L, 233D))
                .willThrow(MenuPlanNotFoundException.class);

        mvc.perform(patch("/workDays/4/menus/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"pos\": 233\n" +
                        "}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("MenuPlan Not Exist")))
        ;
    }
}
