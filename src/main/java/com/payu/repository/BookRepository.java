package com.payu.repository;

import com.payu.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @author Anele Chila
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isbnNumber = :isbnNumber")
    Book findBookByIsbnNumber(@Param("isbnNumber")BigInteger isbnNumber);
}
