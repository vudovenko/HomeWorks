package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;

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
        return author;
    }

    public Map<Character, List<String>> getAuthorsData() {
        Map<Character, List<String>> authorsByFirstLettersName = new HashMap<>();

        for (Character firstLetter = 'A'; firstLetter <= 'Z'; firstLetter++) {
            List<String> authors = jdbcTemplate.query("SELECT * FROM authors WHERE first_name LIKE \'" + firstLetter + "%\'",
                    (ResultSet rs, int rowNum) ->
                            rs.getString("first_name") + " " + rs.getString("last_name"));
            authorsByFirstLettersName.put(firstLetter, authors);
        }
        return new HashMap<>(authorsByFirstLettersName);
    }
}
