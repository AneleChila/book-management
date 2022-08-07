package com.payu.controller;


import com.payu.api.request.CreateBookRequest;
import com.payu.api.request.UpdateBookRequest;
import com.payu.api.response.*;
import com.payu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public CreateBookResponse createBook(@RequestBody CreateBookRequest request,
                                         BindingResult bindingResult) {
        return bookService.saveBook(request, bindingResult);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GetBooksResponse getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public UpdateBookResponse updateBook(@PathVariable("id") Long id,
                                         @RequestBody UpdateBookRequest request,
                                         BindingResult bindingResult) {
        return bookService.updatedBook(request, id, bindingResult);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public GetBooksResponse getAllBooks(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return bookService.findAllBooks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteBookResponse deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBookById(id);
    }
}