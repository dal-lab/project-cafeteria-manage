package com.poppo.dallab.cafeteria.applications;

public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException() {
        super("Menu Not Found");
    }

}
