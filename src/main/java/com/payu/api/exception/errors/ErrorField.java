package com.payu.api.exception.errors;

import lombok.Data;

/**
 * @author Anele Chila
 */
@Data
public class ErrorField {
    private String code;
    private String message;
}
