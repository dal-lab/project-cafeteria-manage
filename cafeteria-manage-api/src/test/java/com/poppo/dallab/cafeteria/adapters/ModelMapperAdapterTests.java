package com.poppo.dallab.cafeteria.adapters;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.interfaces.MenuPlanRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class ModelMapperAdapterTests {

    ModelMapperAdapter modelMapperAdapter;

    @MockBean
    ModelMapper modelMapper;

    @Before
    public void setup() {
        modelMapperAdapter = new ModelMapperAdapter(modelMapper);
    }

    @Test
    public void oneMenuMapperTest() {

        Menu menu = Menu.builder().name("제육볶음").build();
        MenuPlanRequestDto mockMenuPlanRequestDto = MenuPlanRequestDto.builder().name("제육볶음").build();

        given(modelMapper.map(any(), any())).willReturn(menu);

        Menu result = modelMapperAdapter.menuMapping(mockMenuPlanRequestDto);

        assertThat(result.getName()).isEqualTo("제육볶음");

    }

    @Test
    public void manyMenuMapperTest() {

        List<MenuPlanRequestDto> mockMenuPlanRequestDtos = Arrays.asList(
                MenuPlanRequestDto.builder().name("제육볶음").build()
        );

        given(modelMapper.map(any(), any())).willReturn(Menu.builder().name("제육볶음").build());

        List<Menu> menus = modelMapperAdapter.manyMenuMapping(mockMenuPlanRequestDtos);

        assertThat(menus.get(0).getName()).isEqualTo("제육볶음");

    }

}