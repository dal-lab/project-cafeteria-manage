package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.MenuPlanRepository;
import com.poppo.dallab.cafeteria.domain.WorkDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuPlanService {

    private final MenuPlanRepository menuPlanRepository;
    private final WorkDayService workDayService;
    private final MenuService menuService;

    public void addBulkMenu(String workDay, List<Menu> menus) {

        // 테스트 생략!!!
        // 아래꺼 잘 돌아가니까 잘돌겠지!!!
        for (Menu menu : menus) {
            addMenu(workDay, menu.getName());
        }

    }

    public MenuPlan addMenu(String workDay, String menuName) {

        WorkDay foundWorkDay = workDayService.getWorkDayByString(workDay);
        Menu foundMenu = menuService.getMenuByMenuName(menuName);

        MenuPlan menuPlan = MenuPlan.builder()
                .menuId(foundMenu.getId())
                .workDayId(foundWorkDay.getId())
                .build();

        return menuPlanRepository.save(menuPlan);

    }

}
