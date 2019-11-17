package com.poppo.dallab.cafeteria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuPlanResponseDto {

    Long workDayId;

    String day;

    LocalDate date;

    List<MenuResponseDto> menus;

}
