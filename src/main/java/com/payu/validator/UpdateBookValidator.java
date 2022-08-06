package com.payu.validator;

import com.payu.exception.InvalidRequestException;
import com.payu.request.UpdateBookRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.payu.exception.errors.ErrorCodes.FIELD_VALIDATION_ERR;
import static com.payu.exception.errors.ErrorCodes.INVALID_REQUEST;

/**
 * @author Anele Chila
 */
@Component
public class UpdateBookValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        UpdateBookRequest serverRequest = (UpdateBookRequest) obj;

        try {
            Set<ConstraintViolation<UpdateBookRequest>> violations = validator.validate(serverRequest);
            for(ConstraintViolation<UpdateBookRequest> violation  : violations) {
                errors.rejectValue(violation.getPropertyPath().toString(), FIELD_VALIDATION_ERR.getResponseCode(), violation.getMessage());
            }

        } catch (Exception e) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseCode(), errors);
        }
    }

    /**
     * This Validator validates *just* UpdateBookRequest instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateBookRequest.class.isAssignableFrom(clazz);
    }
}
