package com.payu.service;

import com.payu.api.request.CreateBookRequest;
import com.payu.api.request.UpdateBookRequest;
import com.payu.api.response.CreateBookResponse;
import com.payu.api.response.DeleteBookResponse;
import com.payu.api.response.GetBooksResponse;
import com.payu.api.response.UpdateBookResponse;
import org.springframework.validation.BindingResult;

/**
 * @author Anele Chila
 */
public interface BookService {

    GetBooksResponse findAllBooks(int pageNo, int pageSize);
    GetBooksResponse findBookById(Long id);
    CreateBookResponse saveBook(CreateBookRequest request, BindingResult bindingResult);
    UpdateBookResponse updatedBook(UpdateBookRequest request, Long id, BindingResult bindingResult) ;
    DeleteBookResponse deleteBookById(Long id);
}
