package com.payu.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payu.api.request.CreateBookRequest;
import com.payu.api.response.GetBooksResponse;
import com.payu.persistence.model.BookType;
import com.payu.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Anele Chila
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(com.payu.api.controller.BookController.class)
public class BookControllerTest {

    private static final Long TEST_BOOK_ID = 1L;
    private static final String URI = "/v1/books";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Before
    public void init() {
        given(this.bookService.findBookById(TEST_BOOK_ID)).willReturn(new GetBooksResponse());
    }

    @Test
    public void shouldFindABook() throws Exception {
        mockMvc.perform(get(URI, TEST_BOOK_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateBook() throws Exception {
        CreateBookRequest book = new CreateBookRequest();
        book.setPublishDate(new Date());
        book.setName("Atomic Habits");
        book.setIsbnNumber(new BigInteger("7852378586733"));
        book.setPrice(new BigDecimal("78.65"));
        book.setType(BookType.EBook);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(book);
        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString)

        )
                .andExpect(status().isOk());

        book = new CreateBookRequest();
        book.setPublishDate(new Date());
        book.setName("RDPD");
        book.setIsbnNumber(new BigInteger("7852378586733"));
        book.setPrice(new BigDecimal("79.65"));
        book.setType(BookType.EBook);

        mapper = new ObjectMapper();
        jsonString = mapper.writeValueAsString(book);
        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        CreateBookRequest book = new CreateBookRequest();
        book.setPublishDate(new Date());
        book.setName("Atomic Habits");
        book.setIsbnNumber(new BigInteger("7852378586733"));
        book.setPrice(new BigDecimal("78.65"));
        book.setType(BookType.EBook);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(book);
        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString)

        )
                .andExpect(status().isOk());

        mockMvc.perform(delete(URI + "/"+ TEST_BOOK_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        CreateBookRequest book = new CreateBookRequest();
        book.setPublishDate(new Date());
        book.setName("Atomic Habits");
        book.setIsbnNumber(new BigInteger("7852378586733"));
        book.setPrice(new BigDecimal("78.65"));
        book.setType(BookType.EBook);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(book);
        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString)

        )
                .andExpect(status().isOk());

        book = new CreateBookRequest();
        book.setName("Rich Dad Poor Dad");
        book.setIsbnNumber(new BigInteger("7852378586733"));

        mapper = new ObjectMapper();
        jsonString = mapper.writeValueAsString(book);
        mockMvc.perform(put(URI + "/"+ TEST_BOOK_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
    mockMvc.perform(get(URI, TEST_BOOK_ID))
                .andExpect(status().isOk());
    }
}