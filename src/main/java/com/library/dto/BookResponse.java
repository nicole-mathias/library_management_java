package com.library.dto;

public record BookResponse(String isbn, String title, String author, int totalCopies, int availableCopies) {}
