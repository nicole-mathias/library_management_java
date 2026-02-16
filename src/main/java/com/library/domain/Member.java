package com.library.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class Member{
    private int id;
    private MemberType member_type;
    private String name;
    private Map<String, BorrowBook> books_borrowed;
    private int books_allowed_count;
    private int days_allowed;
    private double pendingFees = 0.0;

    public Member(int id, MemberType member_type, String name, int books_allowed_count, int days_allowed) {
        this.id = id;
        this.member_type = member_type;
        this.name = name;
        this.books_borrowed = new HashMap<>();
        this.books_allowed_count = books_allowed_count;
        this.days_allowed = days_allowed;
    }

    public String getName(){
        return name;
    }

    public int getDays_allowed() {
        return days_allowed;
    }

    public void borrow_book(Book book){
        // check if the same book was already borrowed
        if (books_borrowed.containsKey(book.getIsbn())){
            System.out.println("Book was already borrowed...");
            return;
        }

        if (!can_borrow()) {
            System.out.println("Cannot borrow: limit reached.");
            return;
        }
        BorrowBook borrowed_obj = new BorrowBook(this, book);
        books_borrowed.put(book.getIsbn(), borrowed_obj);
    }

    public boolean can_borrow(){
        return books_borrowed.size() < books_allowed_count;
        // TODO: also check for late books
    }

    public void returnBook(Book book){
        String book_isbn = book.getIsbn();

        if (books_borrowed.containsKey(book_isbn)) {
            BorrowBook borrowed_book_obj = books_borrowed.get(book_isbn);
            borrowed_book_obj.setReturnDate();

            double fee = borrowed_book_obj.calculateFees(days_allowed);
            System.out.println("Book returned, Due: $" + fee);
            pendingFees = pendingFees + fee;
            books_borrowed.remove(book_isbn);
        }
    }

    public Map<String, BorrowBook> getBooks_borrowed() {
        return books_borrowed;
    }

    public double getPendingFees() {
        return pendingFees;
    }

    public void payFees(double amount) {
        pendingFees = pendingFees - amount;
        System.out.println("Paid amount: " + amount + ", current pending fees: " + pendingFees);
    }
}