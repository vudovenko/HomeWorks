package com.example.MyBookShopApp.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<BookRating, Integer> {

    public BookRating findBookRatingByBook(Book book);
}
