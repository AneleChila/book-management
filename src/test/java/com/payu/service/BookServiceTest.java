package com.payu.service;

import com.payu.api.exception.BadRequestException;
import com.payu.api.exception.InvalidRequestException;
import com.payu.api.request.CreateBookRequest;
import com.payu.api.request.UpdateBookRequest;
import com.payu.api.response.GetBooksResponse;
import com.payu.api.response.UpdateBookResponse;
import com.payu.persistence.model.BookType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anele Chila
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BookServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @MockBean
    private Validator validator;
    @Autowired
    private BookService bookService;
    private AutoCloseable closeable;

    @Before
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldSaveBook() {
        Long id = createABook();

        assertThat(id).isNotNull();
        assertThat(bookService.findBookById(id)).isNotNull();
    }

    @Test
    public void shouldNotSaveBookWhenDuplicateIsbnNumber() {
        createABook();
        exception.expect(InvalidRequestException.class);
        createABook();
    }

    @Test
    public void shouldUpdateBook() {
        Long id = createABook();

        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setName("Harry Potter");

        UpdateBookResponse response = bookService.updatedBook(updateBookRequest,
                id,
                new BeanPropertyBindingResult(
                        updateBookRequest,
                        "createBookRequest"
                )
        );

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Harry Potter");
        assertThat(response.getId()).isEqualTo(id);
        assertThat(bookService.findBookById(id)).isNotNull();
    }

    @Test
    public void shouldDeleteBook() {
        Long id = createABook();

        bookService.deleteBookById(id);

        exception.expect(BadRequestException.class);
        bookService.findBookById(id);
    }

    @Test
    public void shouldFindAllBooks() {
        int numOfBooks = 5;

        for (int i = 1; i <= 5; i++) {
            createAnyBook();
        }

        GetBooksResponse response = bookService.findAllBooks(0, 10);

        assertThat(response).isNotNull();
        assertThat(response.getBooksList().size()).isEqualTo(numOfBooks);
    }

    private Long createABook() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setPublishDate(new Date());
        createBookRequest.setName("Atomic Habits");
        createBookRequest.setIsbnNumber(new BigInteger("7852378586733"));
        createBookRequest.setPrice(new BigDecimal("78.65"));
        createBookRequest.setType(BookType.EBook);

        return bookService.saveBook(
                createBookRequest,
                new BeanPropertyBindingResult(
                        createBookRequest,
                        "createBookRequest"
                )
        ).getId();
    }

    private void createAnyBook() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setPublishDate(new Date());
        createBookRequest.setName("Atomic Habits");
        createBookRequest.setIsbnNumber(BigInteger.valueOf(new Random().nextInt()));
        createBookRequest.setPrice(new BigDecimal("78.65"));
        createBookRequest.setType(BookType.EBook);

        bookService.saveBook(
                createBookRequest,
                new BeanPropertyBindingResult(
                        createBookRequest,
                        "createBookRequest"
                )
        );
    }
}
