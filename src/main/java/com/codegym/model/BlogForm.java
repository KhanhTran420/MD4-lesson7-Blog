package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {
    private Long id;
    private String name;
    private String author;
    private MultipartFile image;
    private Category category;

    public BlogForm() {
    }

    public BlogForm(Long id, String name, String author, MultipartFile image, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.image = image;
        this.category = category;
    }

    public BlogForm(String name, String author, MultipartFile image, Category category) {
        this.name = name;
        this.author = author;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
