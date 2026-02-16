package com.library.domain;

public class Book{
    private String isbn;
    private String name;
    private String author;
    private int quantity;

    public Book(String isbn, String name, String author, int quantity) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }
}