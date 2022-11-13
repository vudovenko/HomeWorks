package com.example.MyBookShopApp.struct.book;

import com.example.MyBookShopApp.struct.author.entity.Author2;
import com.example.MyBookShopApp.struct.genre.GenreEntity;
import com.example.MyBookShopApp.struct.user.UserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pub_date", columnDefinition = "DATE NOT NULL")
    private Date pubDate;

    @Column(name = "is_bestseller", columnDefinition = "SMALLINT NOT NULL")
    private Byte isBestseller;

    @Column(name = "slug", columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(name = "title", columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", columnDefinition = "INT NOT NULL")
    private Integer price;

    @Column(name = "discount", columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private Byte discount;

    @ManyToMany(mappedBy = "books")
    private List<Author2> authors;

    @ManyToMany(mappedBy = "books")
    private List<GenreEntity> genres;

    @ManyToMany(mappedBy = "books")
    private List<UserEntity> users;

    @ManyToMany(mappedBy = "booksDownloadedByUser")
    private List<UserEntity> usersWhoDownloadedBook;

    @ManyToMany(mappedBy = "booksBoughtByUser")
    private List<UserEntity> usersWhoBoughtBook;

    @ManyToMany(mappedBy = "booksWithReviews")
    private List<UserEntity> usersWhoRoteReview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Byte getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Byte isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }

//    public Set<Author2> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(Set<Author2> authors) {
//        this.authors = authors;
//    }

    @Override
    public String toString() {
        return "Book2{" +
                "id=" + id +
                ", pubDate=" + pubDate +
                ", isBestseller=" + isBestseller +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
