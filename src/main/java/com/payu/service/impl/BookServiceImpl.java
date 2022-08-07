package com.payu.service.impl;

import com.payu.api.exception.BadRequestException;
import com.payu.api.exception.InternalServerErrorException;
import com.payu.api.exception.InvalidRequestException;
import com.payu.api.exception.errors.ErrorCodes;
import com.payu.api.request.CreateBookRequest;
import com.payu.api.request.UpdateBookRequest;
import com.payu.api.response.*;
import com.payu.config.logging.AroundLogger;
import com.payu.persistence.model.Book;
import com.payu.persistence.repository.BookRepository;
import com.payu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import static com.payu.api.exception.errors.ErrorCodes.DUPLICATE_FIELD_ISBN_NUMBER;
import static com.payu.api.exception.errors.ErrorCodes.INVALID_REQUEST;

/**
 * The BookService is responsible for first doing input validation
 * as that needs to be done as soon as possible. The business logic is
 * processed as soon input validation is done. Validation is done on the service
 * instead of the controller to account for situations where the input is not coming
 * only from the controller. This is important in protecting the state of the db.
 *
 * @author Anele Chila
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Validator createBookValidator;
    private final Validator updateBookValidator;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Validator createBookValidator,
                           Validator updateBookValidator) {
        this.bookRepository = bookRepository;
        this.createBookValidator = createBookValidator;
        this.updateBookValidator = updateBookValidator;
    }

    @Override
    @AroundLogger
    public GetBooksResponse findAllBooks(int pageNo, int pageSize) {
        GetBooksResponse response = new GetBooksResponse();
        try {
            Page<Book> page = bookRepository.findAll(PageRequest.of(pageNo, pageSize));
            response.setTotalBooks(page.getTotalElements());
            response.setTotalPages(page.getTotalPages());

            page.get().forEach(book ->
                    response.addBook(new GetBookResponse(book))
            );

            return response;
        } catch (RuntimeException e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @AroundLogger
    public GetBooksResponse findBookById(Long id) {
        GetBooksResponse response = new GetBooksResponse();

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCodes.BOOK_NOT_FOUND.getResponseDesc()));
        response.addBook(new GetBookResponse(book));

        return response;
    }

    @Override
    @AroundLogger
    public CreateBookResponse saveBook(CreateBookRequest request, BindingResult bindingResult) {
        validateCreateRequest(request, bindingResult);

        Book book = new Book();
        book.create(request);

        try {
            return new CreateBookResponse(bookRepository.save(book));
        } catch (RuntimeException e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @AroundLogger
    public UpdateBookResponse updatedBook(UpdateBookRequest request, Long id, BindingResult bindingResult) {
        validateUpdateRequest(request, bindingResult);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCodes.BOOK_NOT_FOUND.getResponseDesc()));
        book.update(request);

        try {
            return new UpdateBookResponse(bookRepository.save(book));
        } catch (RuntimeException e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @AroundLogger
    public DeleteBookResponse deleteBookById(Long id) {
        //check book if exits
        bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCodes.BOOK_NOT_FOUND.getResponseDesc()));
        try {
            bookRepository.deleteById(id);
            return new DeleteBookResponse();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }


    private void validateCreateRequest(CreateBookRequest request, BindingResult bindingResult) {
        createBookValidator.validate(request, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseDesc(), bindingResult);
        }

        if (bookRepository.findBookByIsbnNumber(request.getIsbnNumber()) != null) {
            throw new InvalidRequestException(DUPLICATE_FIELD_ISBN_NUMBER.getResponseDesc(), bindingResult);
        }
    }

    private void validateUpdateRequest(UpdateBookRequest request, BindingResult bindingResult) {
        updateBookValidator.validate(request, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseDesc(), bindingResult);
        }

        if (bookRepository.findBookByIsbnNumber(request.getIsbnNumber()) != null) {
            throw new InvalidRequestException(DUPLICATE_FIELD_ISBN_NUMBER.getResponseDesc(), bindingResult);
        }
    }
}
