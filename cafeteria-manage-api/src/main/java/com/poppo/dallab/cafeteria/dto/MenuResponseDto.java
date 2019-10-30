package com.poppo.dallab.cafeteria.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuResponseDto {

    private Long id;

    private String menuName;

}
