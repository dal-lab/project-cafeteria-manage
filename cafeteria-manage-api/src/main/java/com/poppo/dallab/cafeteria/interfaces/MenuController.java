package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.MenuService;
import com.poppo.dallab.cafeteria.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menus")
    public List<MenuResponseDto> list() {

        List<MenuResponseDto> menus = menuService.getMenus().stream()
                .map(menu -> MenuResponseDto.builder()
                        .menuName(menu.getName())
                        .id(menu.getId())
                        .build())
                .collect(Collectors.toList());

        return menus;
    }

    @PostMapping("/menus")
    public ResponseEntity<?> create(
            @RequestBody MenuRequestDto menuRequestDto
    ) throws URISyntaxException {

        Menu saved = menuService.addMenu(menuRequestDto.getMenuName());

        String url = "/menus/" + saved.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
