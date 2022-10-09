package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {
    private final Logger logger = Logger.getLogger(String.valueOf(BookRepository.class));
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retrieveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(String regex) {
        if (regex == "") {
            return false;
        }

        boolean isItFound = false;
        for (Book book : retrieveAll()) {
            if (Pattern.matches(regex, book.getAuthor())
            || Pattern.matches(regex, book.getTitle())
            || (book.getSize() != null && Pattern.matches(regex, book.getSize().toString()))) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
                isItFound = true;
            }
        }

        return isItFound;
    }
}
