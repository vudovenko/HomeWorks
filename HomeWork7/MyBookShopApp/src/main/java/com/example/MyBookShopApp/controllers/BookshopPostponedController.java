package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("/books")
public class BookshopPostponedController {

    @ModelAttribute(name = "postponedBooks")
    public List<Book> postponedBooks() {
        return new ArrayList<>();
    }

    private final BookRepository bookRepository;

    @Autowired
    public BookshopPostponedController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/postponed")
    public String handleCartRequest(@CookieValue(value = "postponedContents", required = false)
                                    String postponedContents,
                                    Model model) {
        if (postponedContents == null || postponedContents.equals("")) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            postponedContents = postponedContents.startsWith("/")
                    ? postponedContents.substring(1)
                    : postponedContents;
            postponedContents = postponedContents.endsWith("/")
                    ? postponedContents.substring(0, postponedContents.length() - 1)
                    : postponedContents;
            String[] cookieSlugs = postponedContents.split("/");
            List<Book> booksFromCookieSlugs = bookRepository.findBooksBySlugIn(cookieSlugs);
            model.addAttribute("bookPostponed", booksFromCookieSlugs);
        }

        return "postponed";
    }

    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public String handleRemoveBookFromCartRequest(@PathVariable("slug") String slug,
                                                  @CookieValue(name = "postponedContents", required = false) String postponedContents,
                                                  HttpServletResponse response, Model model) {
        if (postponedContents != null && !postponedContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(postponedContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("postponedContents", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }

        return "redirect:/books/cart";
    }

    @PostMapping("/changeBookStatus/postpone/{slug}")
    public String handleChangeBookStatus(@PathVariable("slug") String slug,
                                         @CookieValue(name = "postponedContents", required = false)
                                         String postponedContents,
                                         HttpServletResponse response, Model model) {

        if (postponedContents == null || postponedContents.equals("")) {
            Cookie cookie = new Cookie("postponedContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else if (!postponedContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(postponedContents).add(slug);
            Cookie cookie = new Cookie("postponedContents", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        }

        return "redirect:/books/" + slug;
    }
}
