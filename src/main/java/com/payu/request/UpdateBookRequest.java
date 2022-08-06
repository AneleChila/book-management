package com.payu.request;


import com.payu.model.BookType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Anele Chila
 */
@Getter
@Setter
public class UpdateBookRequest {

    @Size(min = 1, message = "Book name cannot be empty")
    private String name;

    @Min(value = 0, message = "ISBN number should not be less than 0")
    private BigInteger isbnNumber;

    @PastOrPresent(message = "Publish date cannot be in the future")
    private Date publishDate;

    @DecimalMin(value = "0.0", message = "Price should not be less than 0")
    private BigDecimal price;
    private BookType type;
}
