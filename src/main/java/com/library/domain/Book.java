package com.library.domain;

class Book{
    private String issn;
    private String name;
    private String author;
    private int quantity;

    public Book(String issn, String name, String author, int quantity) {
        this.issn = issn;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public String getIssn(){
        return issn;
    }

    public String getName() {
        return name;
    }
}