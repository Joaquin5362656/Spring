package com.example.demo.service;

import com.example.demo.entities.Book;

public class BookPriceCalculator {

    public double calculatePrice(Book book){
        double price = book.getPrice();

        int maxPage = 100;
        int shipment = 10;
        if (book.getPages() > maxPage){
            int increasePerPage = 50;
            price += increasePerPage;
        }
        price += shipment;

        return price;
    }
}
