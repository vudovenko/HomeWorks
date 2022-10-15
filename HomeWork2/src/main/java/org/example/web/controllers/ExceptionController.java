package org.example.web.controllers;

import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.exceptions.EmptyFileException;
import org.example.app.exceptions.InvalidRegExException;
import org.example.web.dto.Book;
import org.example.web.dto.BookIdToRemove;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionController {
    Logger logger = Logger.getLogger(String.valueOf(ExceptionController.class));

    @ExceptionHandler(BookShelfLoginException.class)
    public String handleError(Model model, BookShelfLoginException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(EmptyFileException.class)
    public String emptyFileError(Model model, EmptyFileException exception) {
        logger.info("new EmptyFileException");
        model.addAttribute("emptyFileMessage", exception.getMessage());
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookList", exception.getBookService().getAllBooks());
        return "book_shelf";
    }

    @ExceptionHandler(InvalidRegExException.class)
    public String invalidRegExException(Model model, InvalidRegExException exception) {
        logger.info("new InvalidRegExException");
        model.addAttribute("invalidRegExMessage", exception.getMessage());
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookList", exception.getBookService().getAllBooks());
        return "book_shelf";
    }
}
