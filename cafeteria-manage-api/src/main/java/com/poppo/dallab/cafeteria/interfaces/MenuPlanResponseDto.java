package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.domain.Menu;
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

    String day;

    LocalDate date;

    List<Menu> menus;

}
