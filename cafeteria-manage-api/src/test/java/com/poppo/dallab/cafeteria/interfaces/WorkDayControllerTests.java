package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.applications.WorkDayService;
import com.poppo.dallab.cafeteria.domain.WorkDay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkDayController.class)
public class WorkDayControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WorkDayService workDayService;

    @MockBean
    private MenuPlanService menuPlanService;

    @Test
    public void 일하는_날이_있는_월만_목록으로_가져오기() throws  Exception {

        List<Integer> mockWorkMonthList = Arrays.asList(10);

        given(workDayService.getWorkMonths()).willReturn(mockWorkMonthList);

        mvc.perform(get("/workMonth"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("existedMonthList")))
                .andExpect(content().string(containsString("[")))
                .andExpect(content().string(containsString("10")))
        ;

    }

    @Test
    public void 새로운_달의_시작_요청() throws Exception {

        List<WorkDay> mockWorkDays = Stream.generate(WorkDay::new)
                .limit(30)
                .collect(Collectors.toList());

        given(workDayService.bulkCreate(11)).willReturn(mockWorkDays);

        mvc.perform(post("/workDay")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"menuPlanMonth\": 11}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("30")))
        ;

        verify()

    }

}