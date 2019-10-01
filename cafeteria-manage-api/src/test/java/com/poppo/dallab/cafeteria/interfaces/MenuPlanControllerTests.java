package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.domain.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

        // TODO: anyList 앞에 꼭 Mockito 붙어야 하는지 확인할 것
        verify(menuPlanService).addBulkMenu(eq("2019-09-30"), Mockito.<Menu>anyList());

    }

}
