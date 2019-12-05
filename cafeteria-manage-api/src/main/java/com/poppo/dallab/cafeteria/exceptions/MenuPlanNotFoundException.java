package com.poppo.dallab.cafeteria.exceptions;

public class MenuPlanNotFoundException extends RuntimeException {

    public MenuPlanNotFoundException() {
        super("MenuPlan is not exist");
    }
}
