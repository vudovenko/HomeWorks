package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;
    private AuthorService authorService;

    Logger logger = Logger.getLogger(AuthorService.class.getName());

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate, AuthorService authorService) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorService = authorService;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            String authorFirstName = authorService.getAuthor(rs.getInt("author_id")).getFirstName();
            String authorLastName = authorService.getAuthor(rs.getInt("author_id")).getLastName();
//            logger.info("BookService.getBooksData: autId = " + rs.getInt("author_id")
//                    + ", authorFirstName " + authorFirstName
//                    + " authorLastName " + authorLastName);
//            logger.info("book auth id = " + rs.getInt("author_id") + " bookTitle = " + rs.getString("title"));
            book.setAuthor(authorFirstName + " " + authorLastName);
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("price_old"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }
}
