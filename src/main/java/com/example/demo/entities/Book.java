package com.example.demo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "books")
@Schema(description = "Entity that represents a book in the system")
public class Book {

    // atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-incrementing ID (Dont send on creation)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Full title of the book", example = "White nights", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Author's name", example = "Joaquin Osorio")
    private String author;

    @Schema(description = "Total number of pages", example = "324")
    private Integer pages;

    @Schema(description = "Selling price in local currency", example = "6.75")
    private Double price;

    @Schema(description = "Original publication date", example = "2002-01-29")
    private LocalDate releaseDate;

    @Schema(description = "Indicate if the book is available in digital format", example = "true")
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
