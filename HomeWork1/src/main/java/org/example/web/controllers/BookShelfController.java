package org.example.web.controllers;

import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "books")
public class BookShelfController {
    private Logger logger = Logger.getLogger(String.valueOf(BookShelfController.class));
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        if (!book.getAuthor().isEmpty()
                || !book.getTitle().isEmpty()
                || book.getSize() != null) {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
        } else {
            logger.info("it is not possible to save an EMPTY string");
        }

        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        if (bookService.removeBookById(bookIdToRemove)) {
            logger.info("/remove: the book with id = " + bookIdToRemove + " was successfully deleted");
        } else {
            logger.info("/remove: the book with id = "+ bookIdToRemove + " is not on the shelf");
        }

        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByRegex")
    public String removeBookByRegex(@RequestParam(value = "queryRegex") String regex) {
        if (bookService.removeBookByRegex(regex)) {
            logger.info("/remove: Books matching the pattern = " + regex + " have been deleted");
        } else {
            logger.info("/remove: there is no books matching the pattern = " + regex);
        }

        return "redirect:/books/shelf";
    }
}