package com.payu.service.validator;

import com.payu.api.exception.InvalidRequestException;
import com.payu.api.request.CreateBookRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.payu.api.exception.errors.ErrorCodes.FIELD_VALIDATION_ERR;
import static com.payu.api.exception.errors.ErrorCodes.INVALID_REQUEST;

/**
 * @author Anele Chila
 */
@Component
public class CreateBookValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        CreateBookRequest serverRequest = (CreateBookRequest) obj;

        try {
            Set<ConstraintViolation<CreateBookRequest>> violations = validator.validate(serverRequest);
            for (ConstraintViolation<CreateBookRequest> violation : violations) {
                errors.rejectValue(violation.getPropertyPath().toString(), FIELD_VALIDATION_ERR.getResponseCode(), violation.getMessage());
            }

        } catch (Exception e) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseCode(), errors);
        }
    }

    /**
     * This Validator validates *just* CreateBookRequest instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateBookRequest.class.isAssignableFrom(clazz);
    }
}
