package com.payu.exception;

/**
 * @author Anele Chila
 */
public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String message) {
        super(message);
    }

}
