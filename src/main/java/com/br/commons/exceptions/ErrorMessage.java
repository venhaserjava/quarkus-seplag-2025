package com.br.commons.exceptions;

public class ErrorMessage {

    private String message;
    private int code;

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
