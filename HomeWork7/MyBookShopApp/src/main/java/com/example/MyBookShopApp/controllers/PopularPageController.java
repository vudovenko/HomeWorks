package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BooksPageDto;
import com.example.MyBookShopApp.data.BooksRatingAndPopularityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class PopularPageController {

    private final BooksRatingAndPopularityService bookService;

    @Autowired
    public PopularPageController(BooksRatingAndPopularityService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("popularBooks")
    public List<Book> recentBooks() {
        return bookService.getBooksByPopularity(0, 20).getContent();
    }

    @GetMapping("/popular_page")
    public String popularPage() {
        return "/books/popular";
    }

    @GetMapping("/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getBooksByPopularity(offset, limit).getContent());
    }
}
