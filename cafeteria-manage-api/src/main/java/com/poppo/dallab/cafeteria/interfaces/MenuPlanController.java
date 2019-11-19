package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.applications.WorkDayService;
import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.dto.MenuPlanRequestDto;
import com.poppo.dallab.cafeteria.dto.MenuPlanResponseDto;
import com.poppo.dallab.cafeteria.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MenuPlanController {

    private final WorkDayService workDayService;
    private final MenuService menuService;
    private final MenuPlanService menuPlanService;
    private final ModelMapper modelMapper;

    @GetMapping("/menuPlans")
    public List<MenuPlanResponseDto> getList(
            @PathParam(value = "year") Integer year,
            @PathParam(value = "month") Integer month
    ) {

        List<WorkDay> workDays = workDayService.getWorkDaysByMonth(year, month);

        return workDays.stream().map(workDay -> {

            List<Menu> menus = menuService.getMenusByWorkDayId(workDay.getId());
            List<MenuResponseDto> menuResponseDtos = menus.stream().map(menu ->
                    modelMapper.map(menu, MenuResponseDto.class)
            ).collect(Collectors.toList());

            return MenuPlanResponseDto.builder()
                    .workDayId(workDay.getId())
                    .date(workDay.getDate())
                    .day(workDay.getDay())
                    .menus(menuResponseDtos)
                    .build();
        }).collect(Collectors.toList());

    }

    @GetMapping("/workDay/{date}")
    public MenuPlanResponseDto getOne(@PathVariable(name = "date") String date) {

        WorkDay workDay = workDayService.getWorkDayByString(date);

        List<Menu> menus = menuService.getMenusByWorkDayId(workDay.getId());
        List<MenuResponseDto> menuResponseDtos = menus.stream().map(menu ->
            modelMapper.map(menu, MenuResponseDto.class)
        ).collect(Collectors.toList());

        MenuPlanResponseDto menuPlanResponseDto = MenuPlanResponseDto.builder()
                .date(workDay.getDate())
                .day(workDay.getDay())
                .menus(menuResponseDtos)
                .build();

        return menuPlanResponseDto;
    }

    @PostMapping("/workDays/{workDayId}/menu")
    public ResponseEntity addMenu(
            @PathVariable(name = "workDayId") Long workDayId,
            @RequestBody MenuPlanRequestDto resource
    ) throws URISyntaxException {

        MenuPlan menuPlan = menuPlanService.addMenu(workDayId, resource.getMenuName());

        String url = "/menuPlans/" + menuPlan.getId();

        return ResponseEntity.created(new URI(url)).build();
    }

    @DeleteMapping("/workDays/{workDayId}/menu/{menuId}")
    public void deleteMenuPlan(
            @PathVariable(name = "workDayId") Long workDayId,
            @PathVariable(name = "menuId") Long menuId
    ) {

        menuPlanService.deleteMenuPlan(workDayId, menuId);
    }
}
