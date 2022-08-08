package com.payu.api.exception.errors;

import com.payu.api.response.BookResponse;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Anele Chila
 */
public class ErrorBookResponse extends BookResponse {

    private List<String> globalErrors;
    private Map<String, FieldErrorResource> fieldErrors;

    public ErrorBookResponse(String code, String message) {
        super(code, message);
    }

    public String getCode() {
        return super.getCode();
    }

    public void setCode(String code) {
        super.setCode(code);
    }

    public String getMessage() {
        return super.getMessage();
    }

    public void setMessage(String message) {
        super.setMessage(message);
    }

    public Map<String, FieldErrorResource> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = new LinkedHashMap<>();
        for (FieldErrorResource fieldErrorResource : fieldErrors) {
            this.fieldErrors.put(fieldErrorResource.getField(), fieldErrorResource);
        }
    }

    public void addGlobalError(String message) {
        if (globalErrors == null) {
            globalErrors = new LinkedList<String>();
        }

        globalErrors.add(message);
    }
}