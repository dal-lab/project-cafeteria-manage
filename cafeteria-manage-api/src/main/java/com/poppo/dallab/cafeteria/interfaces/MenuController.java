package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menus")
    public ResponseEntity<?> create(
            @RequestBody MenuRequestDto menuRequestDto
    ) throws URISyntaxException {

        Menu saved = menuService.addMenu(menuRequestDto.getMenuName());

        String url = "/menus/" + saved.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
