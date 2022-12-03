package com.example.MyBookShopApp.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    //NEW BOOK REST REPOSITORY COMMANDS

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    Page<Book> findBooksByPubDateBetween(Date from, Date to, Pageable nextPage);

    Page<Book> findBooksByPubDateLessThanEqual(Date to, Pageable nextPage);

    Page<Book> findBooksByPubDateGreaterThanEqual(Date from, Pageable nextPage);

    Page<Book> findBooksByIsBestsellerEquals(Integer isBestseller, Pageable nextPage);

    @Query(value = "select * from public.books " +
            "where is_bestseller = 1 " +
            "order by number_buys + 0.7 * basket_quantity + 0.4 * number_deferred DESC",
            countQuery = "select * from public.books " +
                    "where is_bestseller = 1 " +
                    "order by number_buys + 0.7 * basket_quantity + 0.4 * number_deferred DESC",
            nativeQuery = true)
    Page<Book> getMostPopularBooks(Pageable nextPage);
}
