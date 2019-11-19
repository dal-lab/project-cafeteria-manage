package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import com.poppo.dallab.cafeteria.exceptions.MenuNotFoundException;
import com.poppo.dallab.cafeteria.exceptions.WorkDayNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuPlanService {

    private final MenuRepository menuRepository;
    private final MenuPlanRepository menuPlanRepository;
    private final WorkDayRepository workDayRepository;

    public MenuPlan addMenu(Long workDayId, String menuName) {

        Menu menu = menuRepository.findByName(menuName).orElseThrow(MenuNotFoundException::new);

        MenuPlan menuPlan = MenuPlan.builder()
                .workDayId(workDayId)
                .menuId(menu.getId())
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
}
