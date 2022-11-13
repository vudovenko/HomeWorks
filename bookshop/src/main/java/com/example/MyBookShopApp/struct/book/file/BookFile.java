package com.example.MyBookShopApp.struct.book.file;

import javax.persistence.*;

@Entity
@Table(name = "book_file")
public class BookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hash", columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(name = "type_id", columnDefinition = "INT NOT NULL")
    private Integer typeId;

    @Column(name = "path", columnDefinition = "VARCHAR(255) NOT NULL")
    private String path;

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

    @Override
    public String toString() {
        return "BookFile{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", typeId=" + typeId +
                ", path='" + path + '\'' +
                '}';
    }
}
