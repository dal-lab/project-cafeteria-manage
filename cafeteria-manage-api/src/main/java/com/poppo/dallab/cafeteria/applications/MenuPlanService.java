package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MenuPlanService {

    MenuPlanRepository menuPlanRepository;

    WorkDayRepository workDayRepository;

    MenuRepository menuRepository;

    @Autowired
    public MenuPlanService(
            MenuPlanRepository menuPlanRepository,
            WorkDayRepository workDayRepository,
            MenuRepository menuRepository
    ) {
        this.menuPlanRepository = menuPlanRepository;
        this.workDayRepository = workDayRepository;
        this.menuRepository = menuRepository;
    }

    public void addBulkMenu(String workDay, List<Menu> menus) {

        // 테스트 생략!!!
        // 아래꺼 잘 돌아가니까 잘돌겠지!!!
        for (Menu menu : menus) {
            addMenu(workDay, menu);
        }

    }

    public MenuPlan addMenu(String workDay, Menu menu) {

        Long workDayId = getWorkDayId(workDay);
        Long menuId = menuRepository.findByName(menu.getName()).getId();

        MenuPlan menuPlan = MenuPlan.builder()
                .menuId(menuId)
                .workDayId(workDayId)
                .build();

        return menuPlanRepository.save(menuPlan);

    }

    public Long getWorkDayId(String workDay) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(workDay, dateTimeFormatter);

        WorkDay foundWorkDay = workDayRepository.findByDate(date);

        return foundWorkDay.getId();

    }

}
