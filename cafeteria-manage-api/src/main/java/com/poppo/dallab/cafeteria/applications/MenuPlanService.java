package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.MenuPlanRepository;
import com.poppo.dallab.cafeteria.domain.MenuRepository;
import com.poppo.dallab.cafeteria.exceptions.NoMenuException;
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

    public MenuPlan addMenu(Long workDayId, String menuName) {

        Menu menu = menuRepository.findByName(menuName).orElseThrow(NoMenuException::new);

        MenuPlan menuPlan = MenuPlan.builder()
                .workDayId(workDayId)
                .menuId(menu.getId())
                .build();

        return menuPlanRepository.save(menuPlan);
    }

    public List<Menu> getMenuByWorkDayId(Long workDayId) {

        return null;
    }
}
