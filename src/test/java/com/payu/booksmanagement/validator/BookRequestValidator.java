package com.payu.booksmanagement.validator;

import com.payu.model.BookType;
import com.payu.request.CreateBookRequest;
import com.payu.request.UpdateBookRequest;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anele Chila
 */
public class BookRequestValidator {

    private Validator createValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Test
    public void shouldValidateCreateBookRequest_FailsValidation() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        createBookRequest.setPublishDate(calendar.getTime());
        createBookRequest.setName("");
        createBookRequest.setIsbnNumber(BigInteger.valueOf(-1));
        createBookRequest.setPrice(new BigDecimal("-1.0"));
        createBookRequest.setType(BookType.EBook);

        Set<ConstraintViolation<CreateBookRequest>> violations = createValidator().validate(createBookRequest);

        assertThat(violations.size()).isEqualTo(4);
    }

    @Test
    public void shouldValidateCreateBookRequest_PassesValidation() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        Calendar calendar = Calendar.getInstance();
        createBookRequest.setPublishDate(calendar.getTime());
        createBookRequest.setName("Atomic Habits");
        createBookRequest.setIsbnNumber(BigInteger.valueOf(1));
        createBookRequest.setPrice(new BigDecimal("0.0"));
        createBookRequest.setType(BookType.EBook);

        Set<ConstraintViolation<CreateBookRequest>> violations = createValidator().validate(createBookRequest);

        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void shouldValidateUpdateBookRequest_FailsValidation() {
        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        updateBookRequest.setPublishDate(calendar.getTime());
        updateBookRequest.setName("");
        updateBookRequest.setIsbnNumber(BigInteger.valueOf(-1));
        updateBookRequest.setPrice(new BigDecimal("-1.0"));
        updateBookRequest.setType(BookType.EBook);

        Set<ConstraintViolation<UpdateBookRequest>> violations = createValidator().validate(updateBookRequest);

        assertThat(violations.size()).isEqualTo(4);
    }

    @Test
    public void shouldValidateUpdateBookRequest_PassesValidation() {
        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        Calendar calendar = Calendar.getInstance();
        updateBookRequest.setPublishDate(calendar.getTime());
        updateBookRequest.setName("Atomic Habits");
        updateBookRequest.setIsbnNumber(BigInteger.valueOf(1));
        updateBookRequest.setPrice(new BigDecimal("0.0"));
        updateBookRequest.setType(BookType.EBook);

        Set<ConstraintViolation<UpdateBookRequest>> violations = createValidator().validate(updateBookRequest);

        assertThat(violations.size()).isEqualTo(0);
    }
}
