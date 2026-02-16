package com.library.dto;

public record BookResponse(String isbn, String title, String author, int totalCopies, int availableCopies) {

    public BookResponse toList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toList'");
    }}
