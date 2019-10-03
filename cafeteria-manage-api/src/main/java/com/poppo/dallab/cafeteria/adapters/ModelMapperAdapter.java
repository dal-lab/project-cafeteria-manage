package com.poppo.dallab.cafeteria.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class ModelMapperAdapter implements Mapper {

    private ModelMapper adaptee;

    @Autowired
    public ModelMapperAdapter(ModelMapper adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public <D> D mapping(Object source, D destination) {

        return adaptee.map(source, (Type) destination.getClass());
    }
}
