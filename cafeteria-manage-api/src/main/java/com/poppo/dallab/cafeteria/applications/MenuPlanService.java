package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import com.poppo.dallab.cafeteria.exceptions.MenuNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.MenuPlanNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.WorkDayNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuPlanService {

    private final MenuRepository menuRepository;
    private final MenuPlanRepository menuPlanRepository;
    private final WorkDayRepository workDayRepository;

    public MenuPlan addMenu(Long workDayId, String menuName, Double pos) {

        Menu menu = menuRepository.findByName(menuName).orElseThrow(MenuNotFoundException::new);

        MenuPlan menuPlan = MenuPlan.builder()
                .workDayId(workDayId)
                .menuId(menu.getId())
                .pos(pos)
                .build();

        return menuPlanRepository.save(menuPlan);
    }

    public String deleteMenuPlan(Long workDayId, Long menuId) {

        // 존재 검증
        workDayRepository.findById(workDayId).orElseThrow(WorkDayNotFoundException::new);
        menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);

        menuPlanRepository.deleteMenuPlansByWorkDayIdAndMenuId(workDayId, menuId);

        return "{}";
    }

    public MenuPlan getMenuPlanByWorkDayIdAndMenuId(Long workDayId, Long menuId) {

        return menuPlanRepository.findByWorkDayIdAndMenuId(workDayId, menuId)
                .orElseThrow(MenuPlanNotFoundException::new);
    }

    public MenuPlan updateMenuPlan(Long workDayId, Long menuId, Double pos) {

        MenuPlan menuPlan = menuPlanRepository.findByWorkDayIdAndMenuId(workDayId, menuId)
                .orElseThrow(MenuPlanNotFoundException::new);

        menuPlan.changePos(pos);

        return menuPlan;
    }

    public List<MenuPlan> getMenuPlansByWorkDayId(Long workDayId) {

        List<MenuPlan> menuPlans = menuPlanRepository.findAllByWorkDayIdOrderByPos(workDayId);
        if(menuPlans.isEmpty()) {
            throw new MenuPlanNotFoundException();
        } else {
            return menuPlans;
        }
    }
}
