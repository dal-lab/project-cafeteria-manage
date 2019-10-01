package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuPlanService {

    MenuPlanRepository menuPlanRepository;

    WorkDayService workDayService;
    MenuService menuService;

    @Autowired
    public MenuPlanService(
            MenuPlanRepository menuPlanRepository,
            WorkDayService workDayService,
            MenuService menuService
    ) {
        this.menuPlanRepository = menuPlanRepository;
        this.workDayService = workDayService;
        this.menuService = menuService;
    }

    public void addBulkMenu(String workDay, List<Menu> menus) {

        // 테스트 생략!!!
        // 아래꺼 잘 돌아가니까 잘돌겠지!!!
        for (Menu menu : menus) {
            addMenu(workDay, menu);
        }

    }

    public MenuPlan addMenu(String workDay, Menu menu) {

        WorkDay foundWorkDay = workDayService.getWorkDayByString(workDay);
        Menu foundMenu = menuService.getMenuByMenuName(menu.getName());

        MenuPlan menuPlan = MenuPlan.builder()
                .menuId(foundMenu.getId())
                .workDayId(foundWorkDay.getId())
                .build();

        return menuPlanRepository.save(menuPlan);

    }

}
