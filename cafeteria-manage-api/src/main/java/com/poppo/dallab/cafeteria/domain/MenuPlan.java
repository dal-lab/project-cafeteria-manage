package com.poppo.dallab.cafeteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workDayId;

    private Long menuId;

    private Double pos;

    public void changeAll(Long workDayId, Long menuId, Double pos) {
        this.workDayId = workDayId;
        this.menuId = menuId;
        this.pos = pos;
    }
}
