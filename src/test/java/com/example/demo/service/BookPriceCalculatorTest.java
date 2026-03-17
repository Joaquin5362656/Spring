package com.example.demo.service;

import com.example.demo.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePriceTest() {

        // test configuration
        Book book = new Book(1L, "Extranjero", "Camus", 120, 200.0, LocalDate.of(2003, 12, 8), true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        // the behavior to be tested is executed
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        // asserts
        assertTrue(price > 0);
        assertEquals(260.0, price);
    }
}