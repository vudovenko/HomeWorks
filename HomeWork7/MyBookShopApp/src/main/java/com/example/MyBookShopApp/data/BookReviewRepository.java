package com.example.MyBookShopApp.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {

    public List<BookReview> findBookReviewsByBook(Book book);
}
