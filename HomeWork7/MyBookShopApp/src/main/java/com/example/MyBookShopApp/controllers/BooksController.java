package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookRepository bookRepository;
    private final BookRatingRepository bookRatingRepository;
    private final BookReviewRepository bookReviewRepository;
    private final ResourceStorage storage;

    @Autowired
    public BooksController(BookRepository bookRepository, BookRatingRepository bookRatingRepository,
                           BookReviewRepository bookReviewRepository, ResourceStorage storage) {
        this.bookRepository = bookRepository;
        this.bookRatingRepository = bookRatingRepository;
        this.bookReviewRepository = bookReviewRepository;
        this.storage = storage;
    }

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model) {
        Book book = bookRepository.findBookBySlug(slug);
        model.addAttribute("slugBook", book);

        BookRating bookRating = bookRatingRepository.findBookRatingByBook(book);
        model.addAttribute("bookRating", bookRating);

        List<BookReview> bookReviews = bookReviewRepository.findBookReviewsByBook(book);
        model.addAttribute("bookReviews", bookReviews);

        return "/books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file,
                                   @PathVariable("slug") String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        Book bootToUpdate = bookRepository.findBookBySlug(slug);
        bootToUpdate.setImage(savePath);
        bookRepository.save(bootToUpdate); // save new path in db here

        return "redirect:/books/" + slug;
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {
        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }

    @PostMapping("/rateBook")
    public String handleChangeInBookRating(@RequestParam("bookId") Integer bookId,
                                           @RequestParam("value") Integer value) {
        Book book = bookRepository.findBookById(bookId);
        BookRating bookRating = bookRatingRepository
                .findBookRatingByBook(book);

        switch (value) {
            case 1:
                bookRating.setRatingOne(bookRating.getRatingOne() + 1);
                break;
            case 2:
                bookRating.setRatingTwo(bookRating.getRatingTwo() + 1);
                break;
            case 3:
                bookRating.setRatingThree(bookRating.getRatingThree() + 1);
                break;
            case 4:
                bookRating.setRatingFour(bookRating.getRatingFour() + 1);
                break;
            case 5:
                bookRating.setRatingFive(bookRating.getRatingFive() + 1);
                break;
            default:
                break;
        }
        bookRatingRepository.save(bookRating);

        return "redirect:/books/" + book.getSlug();
    }

    @PostMapping("/bookReview")
    public String addBookReview(@RequestParam("bookId") Integer bookId,
                                @RequestParam("text") String  text) {
        Book book = bookRepository.findBookById(bookId);

        BookReview bookReview = new BookReview(bookId, "anonymous user", 3,
                new Date(), text, 0, 0, book);

        bookReviewRepository.save(bookReview);

        return "redirect:/books/" + book.getSlug();
    }
}
