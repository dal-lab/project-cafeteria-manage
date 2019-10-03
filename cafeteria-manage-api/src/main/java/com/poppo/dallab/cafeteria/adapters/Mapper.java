package com.poppo.dallab.cafeteria.adapters;

public interface Mapper {

    <D> D mapping(Object source, D destination);

}
