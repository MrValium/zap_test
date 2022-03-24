package com.scaffail.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String description;

    public Book(int id, String title, String author, String description) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getAuthor() {

        return author;
    }

    public String getDescription() {

        return description;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    public String toString() {

        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description + "]";
    }

}
