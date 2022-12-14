package com.example.MyBookShopApp.data;

import javax.persistence.*;

@Entity
@Table(name = "book_file")
public class BookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hash;

    @Column(name = "type_id")
    private Integer typeId;

    private String path;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;

    public String getBookFileExtensionString(){
        return BookFileType.getExtensionStringByTypeID(typeId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
