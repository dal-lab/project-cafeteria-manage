package com.poppo.dallab.cafeteria.adapters;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.interfaces.MenuPlanRequestDto;

public interface Mapper {

    // TODO: 향후 DTO 타입이 많이 추가되면 제네릭 전환 고려??
    Menu menuMapping(MenuPlanRequestDto source);

}
