package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.BooksPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class RecentPageController {

    private final BookService bookService;

    @Autowired
    public RecentPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return bookService.getPageOfBooks(0, 20).getContent();
    }

    @GetMapping("/recent_page")
    public String recentPage() {
        return "books/recent";
    }

    @GetMapping("/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestParam(value = "from", required = false) String from,
                                           @RequestParam(value = "to", required = false) String to) {
        if (from != null && to != null) {
            return new BooksPageDto(bookService.getBooksByDates(offset, limit, from, to).getContent());
        }
        return new BooksPageDto(bookService.getPageOfBooks(offset, limit).getContent());
    }
}
