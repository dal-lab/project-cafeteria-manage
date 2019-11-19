package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.exceptions.MenuNotFoundException;
import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.domain.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuController.class)
public class MenuControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    MenuService menuService;

    @Test
    public void 메뉴_리스트_요청() throws Exception {
        mvc.perform(get("/menus"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")))
        ;

        verify(menuService).getMenus();
    }

    @Test
    public void 존재하는_단일_메뉴_요청() throws Exception {

        Menu mockMenu = Menu.builder()
                .id(1L)
                .name("제육볶음")
                .build();

        given(menuService.getMenuById(1L)).willReturn(mockMenu);

        mvc.perform(get("/menus/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("제육볶음")))
        ;

        verify(menuService).getMenuById(1L);
    }

    @Test
    public void 없는_단일_메뉴_요청() throws Exception {

        given(menuService.getMenuById(44L)).willThrow(MenuNotFoundException.class);

        mvc.perform(get("/menus/44"))
                .andExpect(status().isNotFound())
        ;

    }

    @Test
    public void 메뉴_생성_요청() throws Exception {
        Menu mockMenu = Menu.builder().id(1L).build();

        given(menuService.addMenu("제육볶음")).willReturn(mockMenu);

        mvc.perform(post("/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"menuName\": \"제육볶음\"\n" +
                        "    }"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/menus/1"))
                .andExpect(content().string(containsString("{}")))
        ;

        verify(menuService).addMenu("제육볶음");
    }

    @Test
    public void 메뉴_삭제_요청() throws Exception {
        mvc.perform(delete("/menus/1"))
                .andExpect(status().isOk());

        verify(menuService).removeMenu(eq(1L));
    }

}
