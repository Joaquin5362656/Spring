package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;



@Entity
@Table(name = "books")
public class Book {

    // atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private Double price;
    private LocalDate releaseDate;
    private Boolean onLine;

    // constructors

    public Book() {}

    public Book( Long id, String title, String author, Integer pages, Double price, LocalDate releaseDate, Boolean onLine) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.onLine = onLine;
    }

// getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getOnLine() {
        return onLine;
    }

    public void setOnLine(Boolean onLine) {
        this.onLine = onLine;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
