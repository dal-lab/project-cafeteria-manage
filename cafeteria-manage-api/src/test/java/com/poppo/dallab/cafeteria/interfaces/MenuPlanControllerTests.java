package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.adapters.Mapper;
import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.applications.WorkDayService;
import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.WorkDay;
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
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    Mapper mapper;

    @Test
    public void getList() throws Exception {

        // given
        List<WorkDay> mockWorkDays = new ArrayList<>();
        mockWorkDays.add(WorkDay.builder()
                .id(1L)
                .date(LocalDate.of(2019, 9, 30))
                .day("MONDAY")
                .build());
        given(workDayService.getWorkWeekFromNow()).willReturn(mockWorkDays);

        List<Menu> mockMenus = new ArrayList<>();
        mockMenus.add(Menu.builder().name("제육볶음").build());
        given(menuService.getMenusByWorkDayId(1L)).willReturn(mockMenus);

        // when
        mvc.perform(get("/workDay"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")))
                .andExpect(content().string(containsString("day")))
                .andExpect(content().string(containsString("date")))
                .andExpect(content().string(containsString("2019-09-30")))
                .andExpect(content().string(containsString("MONDAY")))
                .andExpect(content().string(containsString("menus")))
                .andExpect(content().string(containsString("제육볶음")))
            ;

        // then

    }

    @Test
    public void getOne() throws Exception {

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
    public void bulkCreate() throws Exception {

        mvc.perform(post("/workDay/2019-09-30/menuPlans")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "        {\n" +
                        "            \"name\": \"제육볶음\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"name\": \"닭갈비\"\n" +
                        "        }\n" +
                        "    ]"))
                .andExpect(header().stringValues("Location", "/workDay/2019-09-30/menuPlans"))
                .andExpect(status().isCreated());

        verify(menuPlanService).addBulkMenu(eq("2019-09-30"), anyList());

    }

}
