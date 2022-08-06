package com.payu.controller;

import com.payu.model.Book;
import com.payu.request.CreateBookRequest;
import com.payu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anele Chila
 */
@Controller
public class MVPBookController {

    private final BookService bookService;

    @Autowired
    private MVPBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView getAllBook() {
        ModelAndView mav = new ModelAndView("managebook");
        mav.addObject("books", bookService.findAllBooks());
        return mav;
    }

    @RequestMapping(value = "/openAddBookForm")
    public ModelAndView openAddBookForm() {
        ModelAndView mav = new ModelAndView("addbook");
        mav.addObject("book", new Book());
        return mav;
    }

    @RequestMapping(value = "/bookprocess")
    public ModelAndView bookProcess(@ModelAttribute("book") CreateBookRequest book) {
        ModelAndView mav = new ModelAndView("managebook");
        bookService.saveBook(book, null);
        mav.addObject("books", bookService.findAllBooks());
        return mav;
    }

    @RequestMapping(value = "/getSingleBook")
    public ModelAndView editBook(@RequestParam("bookid") Long id) {
        ModelAndView mav = new ModelAndView("addbook");
        mav.addObject("book", bookService.findBookById(id));
        return mav;
    }

    @RequestMapping(value = "/deleteprocess")
    public ModelAndView deleteBook(@RequestParam("bookid") Long id) {
        ModelAndView mav = new ModelAndView("managebook");
        bookService.deleteBookById(id);
        mav.addObject("books", bookService.findAllBooks());
        return mav;
    }
}