package com.payu.api.exception.errors;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anele Chila
 */
@Data
public class Error {
    private List<ErrorField> errors = new ArrayList<>();
}
