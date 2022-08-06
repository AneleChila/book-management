package com.payu.api.response;

import com.payu.persistence.model.Book;
import com.payu.persistence.model.BookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Anele Chila
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBookResponse {

    private Long id;
    private String name;
    private BigInteger isbnNumber;
    private Date publishDate;
    private BigDecimal price;
    private BookType type;

    public GetBookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.isbnNumber = book.getIsbnNumber();
        this.type = book.getType();
        this.price = book.getPrice();
        this.publishDate = book.getPublishDate();
    }
}
