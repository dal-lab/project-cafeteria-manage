package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.adapters.Mapper;
import com.poppo.dallab.cafeteria.applications.MenuPlanService;
import com.poppo.dallab.cafeteria.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class MenuPlanController {

    MenuPlanService menuPlanService;

    Mapper mapper;

    @Autowired
    public MenuPlanController(MenuPlanService menuPlanService, Mapper mapper) {
        this.menuPlanService = menuPlanService;
        this.mapper = mapper;
    }

    @PostMapping("/workDay/{date}/menuPlans")
    public ResponseEntity bulkCreate(
            @PathVariable(name = "date") String date,
            @RequestBody List<MenuPlanRequestDto> menuPlanRequestDtos
        ) throws URISyntaxException {

        String url = "/workDay/"+ date +"/menuPlans";

        List<Menu> menus = mapper.manyMenuMapping(menuPlanRequestDtos);

        menuPlanService.addBulkMenu(date, menus);

        return ResponseEntity.created(new URI(url)).body("{}");

    }

}
