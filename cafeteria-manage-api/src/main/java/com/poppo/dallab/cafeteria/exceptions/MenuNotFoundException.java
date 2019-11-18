package com.poppo.dallab.cafeteria.exceptions;

public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException() {
        super("Menu Not Found");
    }

}
