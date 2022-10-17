package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;
    Logger logger = Logger.getLogger(AuthorService.class.getName());

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Author getAuthor(Integer id) {
        Author author = jdbcTemplate.query(String.format("SELECT * FROM authors WHERE id = '%d'", id), (ResultSet rs, int rowNum) -> {
            Author aut = new Author();
            aut.setId(rs.getInt("id"));
            aut.setFirstName(rs.getString("first_name"));
            aut.setLastName(rs.getString("last_name"));
            return aut;
        }).get(0);
//        logger.info("AuthorService.getAuthor: author = " + author);
        return author;
    }

    public List<Author> getAuthorsData() {
//        Map<Character, List<String>> authorsByFirstLettersName = new HashMap<>();

//        for (int i = 128; i < 160; i++) {
//            Character firstLetter = (char)i;
//            List<String> authors = jdbcTemplate.query(String.format("SELECT * FROM authors WHERE first_name LIKE '%s'", firstLetter),
//                    (ResultSet rs, int rowNum) -> {
//                Author author = new Author();
//                author.setId(rs.getInt("id"));
//                author.setFirstName(rs.getString("first_name"));
//                author.setLastName(rs.getString("last_name"));
//                return author.toString();
//            });
//
//            authorsByFirstLettersName.put(firstLetter, new )
//        }

        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            return author;
        });
//        logger.info("AuthorService.getAuthorsData: authors count = " + authors.size());
        return new ArrayList<>(authors);
    }
}
