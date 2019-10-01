package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuPlanController.class)
public class MenuPlanControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    MenuPlanService menuPlanService;

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
