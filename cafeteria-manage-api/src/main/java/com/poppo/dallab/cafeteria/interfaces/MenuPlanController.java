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
import com.poppo.dallab.cafeteria.dto.WorkDayUpdateRequestDto;
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
@RequiredArgsConstructor
public class MenuPlanController {

    private final WorkDayService workDayService;
    private final MenuService menuService;
    private final MenuPlanService menuPlanService;
    private final ModelMapper modelMapper;

    @GetMapping("/menuPlans")
    public List<MenuPlanResponseDto> getList(
            @PathParam(value = "year") Integer year,
            @PathParam(value = "month") Integer month,
            @PathParam(value = "weekCount") Integer weekCount
    ) {

        List<WorkDay> workDays = workDayService.getWorkDaysByMonth(year, month, weekCount);

        return workDays.stream().map(workDay -> {

            List<MenuPlan> menuPlans = menuPlanService.getMenuPlansByWorkDayId(workDay.getId());
            List<Menu> menus = menuService.getMenusByWorkDayId(workDay.getId());

            List<MenuResponseDto> menuResponseDtos = menuPlans.stream().map(menuPlan -> {
                List<Menu> filteredMenu = menus.stream()
                        .filter(menu -> menu.getId().equals(menuPlan.getMenuId()))
                        .collect(Collectors.toList());
                MenuResponseDto menuResponseDto = modelMapper.map(filteredMenu.get(0), MenuResponseDto.class);

                menuResponseDto.setMenuPlanId(menuPlan.getId());
                menuResponseDto.setPos(menuPlan.getPos());

                return menuResponseDto;
            }).collect(Collectors.toList());

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

        MenuPlan menuPlan = menuPlanService.addMenu(workDayId, resource.getMenuName(), resource.getPos());

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

    // TODO: MenuPlan ID 받아서 처리하도록 변경 필요
    @PutMapping("/menuPlans/{menuPlanId}")
    public String updatePos(
            @PathVariable(name = "menuPlanId") Long menuPlanId,
            @RequestBody WorkDayUpdateRequestDto resource
    ) {

        MenuPlan menuPlan = menuPlanService.updateMenuPlan(
                menuPlanId, resource.getWorkDayId(), resource.getMenuId(), resource.getPos());

        return "/menuPlans/" + menuPlan.getId();
    }
}
