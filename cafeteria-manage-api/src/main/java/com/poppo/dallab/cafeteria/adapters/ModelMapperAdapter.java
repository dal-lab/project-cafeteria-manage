package com.poppo.dallab.cafeteria.adapters;

import com.poppo.dallab.cafeteria.domain.Menu;
import com.poppo.dallab.cafeteria.interfaces.MenuPlanRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperAdapter implements Mapper {

    private ModelMapper adaptee;

    @Autowired
    public ModelMapperAdapter(ModelMapper adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public Menu menuMapping(MenuPlanRequestDto source) {

        Menu menu = adaptee.map(source, Menu.class);

        return menu;
    }
}
