package com.library.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Library{

    private Map<String,Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public Library(Map<String, Book> books) {
        this.books = books != null ? new HashMap<>(books) : new HashMap<>();
    }

    public void addBook(Book book) {
        this.books.put(book.getIsbn(), book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public void borrowBook(Member member, Book book) {
        if (!books.containsKey(book.getIsbn())) {
            System.out.println("Book not in library catalog.");
            return;
        }
        member.borrow_book(book);
    }

    public Book getBookByName(String name){
        String normalizedName = name.strip().toLowerCase();
        for (Map.Entry<String, Book> entry :this.books.entrySet()) {
            String isbn = entry.getKey();
            Book book = entry.getValue();

            // make name lower case while comparing and remove and extra spaces
            if (book.getName().strip().toLowerCase().equals(normalizedName)) {
                return book;
            }
        }
        return null;
    }

    public Book getBookByIsbn(String isbn) {
        return books.get(isbn);
    }


    public void returnBook(Member member, Book book){
        // So there are two paths here
        // 1) check if book was borrowed - if true you can do stuff like calculate late fees and validate thihngs
        // 2) if a person has a book (which was not borrowed), which means they were in the library and just picked it up -->
        // they can still return a book - which have any checks done

        // check if the provided book is valid and exists in the inventory of books
        String book_isbn = book.getIsbn();

        if (!this.books.containsKey(book_isbn)){
            System.out.println("Invalid book...");
            return;
        }

        // check if the book was ever borrowed
        // you need the member instance - to check if the book was ever borrowed in the past
        member.returnBook(book);
    }
}