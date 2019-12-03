package com.poppo.dallab.cafeteria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDto {

    private Long id;

    private String menuName;

    private Double pos;

}
