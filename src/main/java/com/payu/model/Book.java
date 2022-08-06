package com.payu.model;


import com.payu.request.CreateBookRequest;
import com.payu.request.UpdateBookRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Anele Chila
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(name = "isbn_number")
    private BigInteger isbnNumber;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "type")
    private BookType type;

    public void update(UpdateBookRequest request) {
        this.setName(request.getName() == null ? this.getName() : request.getName());
        this.setLastModifiedDate(new Date());
        this.isbnNumber = request.getIsbnNumber() == null ? this.isbnNumber : request.getIsbnNumber();
        this.type = request.getType() == null ? this.type : request.getType();
        this.price = request.getPrice() == null ? this.price : request.getPrice();
        this.publishDate = request.getPublishDate() == null ? this.publishDate : request.getPublishDate();
    }

    public void create(CreateBookRequest request) {
        this.setName(request.getName());
        this.setLastModifiedDate(new Date());
        this.setCreateDate(new Date());
        this.isbnNumber = request.getIsbnNumber();
        this.type = request.getType();
        this.price = request.getPrice();
        this.publishDate = request.getPublishDate();
    }
}
