package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.domain.MenuPlan;
import com.poppo.dallab.cafeteria.domain.MenuPlanRepository;
import com.poppo.dallab.cafeteria.domain.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuPlanRepository menuPlanRepository;

    public Menu getMenuByMenuName(String name) {

        return menuRepository.findByName(name);
    }

    public List<Menu> getMenusByWorkDayId(Long workDayId) {

        List<MenuPlan> menuPlans = menuPlanRepository.findAllByWorkDayId(workDayId);

        List<Menu> menus = menuPlans.stream()
                // 여기 예외 처리 어떻게 할지 고민
                // 아니 일단 너무 더러운데...
                .map(menuPlan -> menuRepository.findById(menuPlan.getMenuId()).orElseThrow(null))
                .collect(Collectors.toList());

        return menus;
    }

    public List<Menu> getMenus() {

        return menuRepository.findAll();
    }

    public Menu addMenu(String menuName) {
        Menu menu = Menu.builder().name(menuName).build();

        return menuRepository.save(menu);
    }
}
