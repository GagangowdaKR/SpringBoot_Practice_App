package com.sparksupport.SparkTraining.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @NotNull(message = "Book Title should not be null")
    private String title;

//    @NotNull(message = "authorId should not be null")
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

//    @NotNull(message = "Category Id should not be null")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Book(Category category, Author author, String title) {
        this.category = category;
        this.author = author;
        this.title = title;
    }
}