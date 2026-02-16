package com.library.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowBook {
    private Book book;
    private LocalDate borrowedDate = LocalDate.now();
    private LocalDate returnDate;
    private Member member;

    public BorrowBook(Member member, Book book){
        this.member = member;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setReturnDate() {
        this.returnDate = LocalDate.now();
    }

    private static final double FEE_PER_DAY = 1.0;

    public double calculateFees(int days_allowed) {
        if (returnDate == null) setReturnDate();
        long total_days = ChronoUnit.DAYS.between(borrowedDate, returnDate);
        if (total_days > days_allowed) {
            return (total_days - days_allowed) * FEE_PER_DAY;
        }
        return 0.0;
    }
}