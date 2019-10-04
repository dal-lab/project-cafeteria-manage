package com.poppo.dallab.cafeteria.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperAdapter implements Mapper {

    private ModelMapper adaptee;

    @Autowired
    public ModelMapperAdapter(ModelMapper adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public <D> D mapping(Object source, Class<D> destinationType) {

        return adaptee.map(source, destinationType);
    }
}
