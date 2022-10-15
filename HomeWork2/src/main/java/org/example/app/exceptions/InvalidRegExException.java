package org.example.app.exceptions;

import org.example.app.services.BookService;

public class InvalidRegExException extends Exception {
    private final String message;
    private final BookService bookService;


    public InvalidRegExException(String message, BookService bookService) {
        this.message = message;
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return bookService;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
