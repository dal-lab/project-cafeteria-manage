package com.poppo.dallab.cafeteria.interfaces;

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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
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

    @Test
    public void 새로운_달의_시작_요청() throws Exception {

        List<WorkDay> mockWorkDays = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            mockWorkDays.add(WorkDay.builder().build());
        }

        given(workDayService.bulkCreate(11)).willReturn(mockWorkDays);

        mvc.perform(post("/workDay")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"menuPlanMonth\": 11}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("30")))
        ;

    }

}