package com.payu.exception.errors;

import org.springframework.http.HttpStatus;

/**
 * @author Anele Chila
 */
public enum ErrorCodes {

    BAD_REQUEST(1, "Bad Request", HttpStatus.BAD_REQUEST),
    GENERAL_DATABASE_ERR(2, "General Database Error", HttpStatus.INTERNAL_SERVER_ERROR),
    GENERAL_SYSTEM_ERR(3, "General System Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FIELD_VALIDATION_ERR(4, "Invalid field: ", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(5, "Invalid Request", HttpStatus.BAD_REQUEST),
    BOOK_NOT_FOUND(6,"Book does not exist", HttpStatus.BAD_REQUEST),
    DUPLICATE_FIELD_ISBN_NUMBER(7, "Duplicate field : Isbn number", HttpStatus.BAD_REQUEST);


    private String responseCode;
    private String responseDesc;
    private HttpStatus httpStatus;
    private static final String RESPONSE_CODE_FORMAT = "%04d";

    ErrorCodes(int responseCode, String responseDesc, HttpStatus httpStatus) {
        if (responseCode >= 0) {
            this.responseCode = String.format(RESPONSE_CODE_FORMAT, responseCode);
        } else {
            this.responseCode = String.valueOf(responseCode);
        }
        this.responseDesc = responseDesc;
        this.httpStatus = httpStatus;
    }


    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
