package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BooksRatingAndPopularityService {

    private BookRepository bookRepository;

    @Autowired
    public BooksRatingAndPopularityService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBooksByPopularity(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.getMostPopularBooks(nextPage);
    }
}
