package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuPlanRepository extends CrudRepository<MenuPlan, Long> {

    List<MenuPlan> findAllByWorkDayIdOrderByPos(Long workDayId);

    Optional<MenuPlan> findByWorkDayIdAndMenuId(Long workDayId, Long menuId);

    void deleteMenuPlansByWorkDayIdAndMenuId(Long workDayId, Long menuId);
}
