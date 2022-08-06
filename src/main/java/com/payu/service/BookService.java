package com.payu.service;

import com.payu.request.CreateBookRequest;
import com.payu.request.UpdateBookRequest;
import com.payu.response.CreateBookResponse;
import com.payu.response.DeleteBookResponse;
import com.payu.response.GetBooksResponse;
import com.payu.response.UpdateBookResponse;
import org.springframework.validation.BindingResult;

/**
 * @author Anele Chila
 */
public interface BookService {

    GetBooksResponse findAllBooks();
    GetBooksResponse findBookById(Long id);
    CreateBookResponse saveBook(CreateBookRequest request, BindingResult bindingResult);
    UpdateBookResponse updatedBook(UpdateBookRequest request, Long id, BindingResult bindingResult) ;
    DeleteBookResponse deleteBookById(Long id);
}
