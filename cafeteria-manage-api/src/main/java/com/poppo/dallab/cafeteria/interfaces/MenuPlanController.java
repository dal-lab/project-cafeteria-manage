package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.adapters.Mapper;
import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.applications.WorkDayService;
import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.dto.MenuPlanRequestDto;
import com.poppo.dallab.cafeteria.dto.MenuPlanResponseDto;
import lombok.RequiredArgsConstructor;
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
    private final Mapper mapper;

    @GetMapping("/menuPlans")
    public List<MenuPlanResponseDto> getList(
            @PathParam(value = "year") Integer year,
            @PathParam(value = "month") Integer month
    ) {

        List<WorkDay> workDays = workDayService.getWorkDaysByMonth(year, month);

        return workDays.stream().map(workDay -> {
            return MenuPlanResponseDto.builder()
                    .workDayId(workDay.getId())
                    .date(workDay.getDate())
                    .day(workDay.getDay())
                    .build();
        }).collect(Collectors.toList());

    }

    @GetMapping("/workDay/{date}")
    public MenuPlanResponseDto getOne(@PathVariable(name = "date") String date) {

        WorkDay workDay = workDayService.getWorkDayByString(date);
        List<Menu> menus = menuService.getMenusByWorkDayId(workDay.getId());

        MenuPlanResponseDto menuPlanResponseDto = MenuPlanResponseDto.builder()
                .date(workDay.getDate())
                .day(workDay.getDay())
                .menus(menus)
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

}
