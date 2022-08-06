package com.payu.api.controller;

import com.payu.api.exception.BadRequestException;
import com.payu.api.exception.InternalServerErrorException;
import com.payu.api.exception.InvalidRequestException;
import com.payu.api.exception.errors.ErrorBookResponse;
import com.payu.api.exception.errors.ErrorCodes;
import com.payu.api.exception.errors.FieldErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anele Chila
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

  @ExceptionHandler({ InvalidRequestException.class })
  protected ResponseEntity<Object> handleInvalidRequest(InvalidRequestException ire, WebRequest request) {
  	logger.info("InvalidRequestException caught", ire);
      List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

      List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
      for (FieldError fieldError : fieldErrors) {
          FieldErrorResource fieldErrorResource = new FieldErrorResource();
          fieldErrorResource.setResource(fieldError.getObjectName());
          fieldErrorResource.setField(fieldError.getField());
          fieldErrorResource.setCode(fieldError.getCode());
          fieldErrorResource.setMessage(fieldError.getDefaultMessage());
          fieldErrorResources.add(fieldErrorResource);
      }

      ErrorBookResponse error = new ErrorBookResponse(ErrorCodes.INVALID_REQUEST.getResponseCode(), ire.getMessage());
      error.setFieldErrors(fieldErrorResources);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      return handleExceptionInternal(ire, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
  }

  @ExceptionHandler( { BadRequestException.class } )
  protected ResponseEntity<Object> handleBadRequest(BadRequestException bre, WebRequest request) {
  	logger.info("BadRequestException caught", bre);

      ErrorBookResponse error = new ErrorBookResponse(ErrorCodes.BAD_REQUEST.getResponseCode(), bre.getMessage());
      error.addGlobalError(bre.getMessage());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(bre, error, headers, HttpStatus.BAD_REQUEST, request);
  }

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    protected ResponseEntity<Object> handleInternalServerError(InternalServerErrorException e, WebRequest request) {

        ErrorBookResponse error = new ErrorBookResponse(ErrorCodes.GENERAL_SYSTEM_ERR.getResponseCode(), e.getMessage());
        error.addGlobalError(e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
