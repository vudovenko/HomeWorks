package com.example.MyBookShopApp.struct.user;

import com.example.MyBookShopApp.struct.book.Book2;
import com.example.MyBookShopApp.struct.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.struct.book.review.MessageEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "date NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "book2user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book2> books;

    @ManyToMany
    @JoinTable(
            name = "file_download",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book2> booksDownloadedByUser;

    @ManyToMany
    @JoinTable(
            name = "balance_transaction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book2> booksBoughtByUser;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<MessageEntity> messages;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL)
    private UserContactEntity contact;

    @ManyToMany
    @JoinTable(
            name = "book_review",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book2> booksWithReviews;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<BookReviewLikeEntity> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book2> getBooks() {
        return books;
    }

    public void setBooks(List<Book2> books) {
        this.books = books;
    }

    public List<Book2> getBooksDownloadedByUser() {
        return booksDownloadedByUser;
    }

    public void setBooksDownloadedByUser(List<Book2> booksDownloadedByUser) {
        this.booksDownloadedByUser = booksDownloadedByUser;
    }

    public List<Book2> getBooksBoughtByUser() {
        return booksBoughtByUser;
    }

    public void setBooksBoughtByUser(List<Book2> booksBoughtByUser) {
        this.booksBoughtByUser = booksBoughtByUser;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public UserContactEntity getContact() {
        return contact;
    }

    public void setContact(UserContactEntity contact) {
        this.contact = contact;
    }
}
