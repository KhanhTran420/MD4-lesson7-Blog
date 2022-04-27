package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog() {
    }

    public Blog(Long id, String name, String author, String image, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.image = image;
        this.category = category;
    }

    public Blog(String name, String author, String image, Category category) {
        this.name = name;
        this.author = author;
        this.image = image;
        this.category = category;
    }

    public Blog(Long id, String name, String author, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public Blog(String name, String author, Category category) {
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
