package com.poppo.dallab.cafeteria.exceptions;

public class WeekCountExceedException extends RuntimeException {

    public WeekCountExceedException() {

        super("Request WeekCount is too big");
    }
}
