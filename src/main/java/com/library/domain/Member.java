package com.library.domain;

import java.util.HashMap;
import java.util.Map;

abstract class Member{
    private String id;
    private MemberType member_type;
    private String name;
    private Map<String, BorrowBook> books_borrowed;
    private int books_allowed_count;
    private int days_allowed;
    private double pendingFees = 0.0;

    public Member(String id, MemberType member_type, String name, int books_allowed_count, int days_allowed) {
        this.id = id;
        this.member_type = member_type;
        this.name = name;
        this.books_borrowed = new HashMap<>();
        this.books_allowed_count = books_allowed_count;
        this.days_allowed = days_allowed;
    }

    public void borrow_book(Book book){
        // check if the same book was already borrowed
        if (books_borrowed.containsKey(book.getIssn())){
            System.out.println("Book was already borrowed...");
            return;
        }

        if (!can_borrow()) {
            System.out.println("Cannot borrow: limit reached.");
            return;
        }
        BorrowBook borrowed_obj = new BorrowBook(this, book);
        books_borrowed.put(book.getIssn(), borrowed_obj);
    }

    public boolean can_borrow(){
        return books_borrowed.size() < books_allowed_count;
        // TODO: also check for late books
    }

    public void returnBook(Book book){
        String book_issn = book.getIssn();

        if (books_borrowed.containsKey(book_issn)) {
            BorrowBook borrowed_book_obj = books_borrowed.get(book_issn);
            borrowed_book_obj.setReturnDate();

            double fee = borrowed_book_obj.calculateFees(days_allowed);
            System.out.println("Book returned, Due: $" + fee);
            pendingFees = pendingFees + fee;
            books_borrowed.remove(book_issn);
        }
    }

    public double getPendingFees() {
        return pendingFees;
    }

    public void payFees(double amount) {
        pendingFees = pendingFees - amount;
        System.out.println("Paid amount: " + amount + ", current pending fees: " + pendingFees);
    }
}

class Student extends Member{
    private static final int BOOKS_ALLOWED_COUNT = 5;
    private static final int DAYS_ALLOWED = 15;

    public Student(String id, String name){
        super(id, MemberType.StudentMember, name, BOOKS_ALLOWED_COUNT, DAYS_ALLOWED);
    }
}

class Staff extends Member{
    private static final int BOOKS_ALLOWED_COUNT = 10;
    private static final int DAYS_ALLOWED = 30;

    public Staff(String id, String name){
        super(id, MemberType.StaffMember, name, BOOKS_ALLOWED_COUNT, DAYS_ALLOWED);
    }
}