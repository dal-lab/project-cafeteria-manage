package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuPlanRepository extends CrudRepository<MenuPlan, Long> {

    List<MenuPlan> findAllByWorkDayId(Long workDayId);

    void deleteMenuPlansByWorkDayIdAndMenuId(Long workDayId, Long menuId);

    MenuPlan findByWorkDayIdAndMenuId(Long workDayId, Long menuId);
}
