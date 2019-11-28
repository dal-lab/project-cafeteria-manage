package com.poppo.dallab.cafeteria.exceptions;

public class WorkDayNotFoundException extends RuntimeException {

    public WorkDayNotFoundException() {

        super("This Day Not Exist");
    }
}
