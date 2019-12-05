package com.poppo.dallab.cafeteria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MenuNotFoundException.class)
    public String handleMenuNotFound() {

        return "Menu Not Found";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WorkDayNotFoundException.class)
    public String handleWorkDayNotFound() {

        return "WorkDay Not Found";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WeekCountExceedException.class)
    public String handleWeekCountExceed() {

        return "Request WeekCount is too big";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MenuPlanNotFoundException.class)
    public String handleMenuPlanNotFoundException() {

        return "MenuPlan Not Exist";
    }
}
