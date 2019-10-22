package com.poppo.dallab.cafeteria.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu, Long> {

    Menu findByName(String name);

    Optional<Menu> findById(Long id);

    Menu save(Menu menu);

}
