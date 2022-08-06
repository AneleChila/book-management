package com.payu.controller;


import com.payu.request.CreateBookRequest;
import com.payu.request.UpdateBookRequest;
import com.payu.response.BookResponse;
import com.payu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author Anele Chila
 */
@RestController
@RequestMapping("/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public BookResponse createBook(@RequestBody CreateBookRequest request,
                                       BindingResult bindingResult) {
        return bookService.saveBook(request, bindingResult);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookResponse getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public BookResponse updateBook(@PathVariable("id") Long id,
                                       @RequestBody UpdateBookRequest request,
                                       BindingResult bindingResult) {
        return bookService.updatedBook(request, id, bindingResult);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BookResponse getAllBooks() {
        return bookService.findAllBooks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BookResponse deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBookById(id);
    }
}