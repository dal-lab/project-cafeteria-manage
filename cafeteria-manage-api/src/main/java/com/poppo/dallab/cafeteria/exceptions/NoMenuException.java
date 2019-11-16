package com.poppo.dallab.cafeteria.exceptions;

public class NoMenuException extends RuntimeException {

    public NoMenuException() {
        super("Menu Not Exist");
    }
}
