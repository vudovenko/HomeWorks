package org.example.web.dto;

import javax.validation.constraints.*;

public class Book {
    private Integer id;
    @Size(min = 2, max = 30)
    @NotBlank
    private String author;
    @Size(min = 2, max = 100)
    @NotBlank
    private String title;
    @Digits(integer = 4, fraction = 0)
    @Min(value = 1)
    @NotNull
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
