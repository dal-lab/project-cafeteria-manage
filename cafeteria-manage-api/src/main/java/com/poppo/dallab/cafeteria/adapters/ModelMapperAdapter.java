package com.poppo.dallab.cafeteria.adapters;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelMapperAdapter implements Mapper {

    private final ModelMapper adaptee;

    @Override
    public <D> D mapping(Object source, Class<D> destinationType) {

        return adaptee.map(source, destinationType);
    }
}
