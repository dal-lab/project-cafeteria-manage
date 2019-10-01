package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.domain.Menu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MenuPlanRequestDtoTests {

    ModelMapper modelMapper;

    @Before
    public void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    public void oneMenuPlanRequestDtoMappingTest() {

        MenuPlanRequestDto mockPlanRequestDto = MenuPlanRequestDto.builder()
                .name("제육볶음")
                .build();

        Menu mapped = modelMapper.map(mockPlanRequestDto, Menu.class);

        assertThat(mapped.getName()).isEqualTo("제육볶음");

    }

    @Test
    public void manyMenuPlanRequestDtoMappingTest() {

        List<MenuPlanRequestDto> menuPlanRequestDtos = new ArrayList<>();
        MenuPlanRequestDto mockPlanRequestDto1 = MenuPlanRequestDto.builder()
                .name("제육볶음")
                .build();
        MenuPlanRequestDto mockPlanRequestDto2 = MenuPlanRequestDto.builder()
                .name("오징어볶음")
                .build();
        menuPlanRequestDtos.add(mockPlanRequestDto1);
        menuPlanRequestDtos.add(mockPlanRequestDto2);

        List<Menu> mappedList = menuPlanRequestDtos.stream()
                .map(menuPlanRequestDto -> {
                    return modelMapper.map(menuPlanRequestDto, Menu.class);
                })
                .collect(Collectors.toList());

        assertThat(mappedList.get(0).getName()).isEqualTo("제육볶음");

    }

}