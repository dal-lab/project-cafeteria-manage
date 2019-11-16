package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu, Long> {

    List<Menu> findAll();

    Optional<Menu> findByName(String name);

    Optional<Menu> findById(Long id);

    Menu save(Menu menu);

    void deleteById(Long menuId);
}
