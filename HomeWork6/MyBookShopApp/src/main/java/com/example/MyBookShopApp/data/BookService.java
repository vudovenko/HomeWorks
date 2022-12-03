package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(String.valueOf(BookService.class));

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    //NEW BOOK SEVICE METHODS

    public List<Book> getBooksByAuthor(String authorName){
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceOldBetween(min,max);
    }

    public List<Book> getBooksWithPrice(Integer price){
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice(){
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers(){
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBookByTitleContaining(searchWord,nextPage);
    }

    public Page<Book> getBooksByDates(Integer offset, Integer limit, String from, String to) {
        Pageable nextPage = PageRequest.of(offset, limit);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        logger.info("\nBookService: offset = " + offset + ", limit = "
                + limit + ", from = " + from + ", to = " + to);

        try {
            if (from == "" && to == "") {
                return bookRepository.findAll(nextPage);
            } else if (from == "") {
                return bookRepository.findBooksByPubDateLessThanEqual(dateFormat.parse(to), nextPage);
            } else if (to == "") {
                return bookRepository.findBooksByPubDateGreaterThanEqual(dateFormat.parse(from), nextPage);
            }
            logger.info("\ndateFrom = " + dateFormat.parse(from) + ", dateTo = " + dateFormat.parse(to) + "\n");
            return bookRepository
                    .findBooksByPubDateBetween(dateFormat.parse(from), dateFormat.parse(to), nextPage);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
