package com.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.domain.Book;
import com.library.dto.BookResponse;
import com.library.dto.CreateBookRequest;

@Service
public class BookService {

    public final LibraryState state;

    public BookService(LibraryState state) {
        this.state = state;
    }

    public BookResponse addBook(CreateBookRequest req) {
        // dto based record
        Book book = new Book(req.isbn(), req.title(), req.author(), req.copies());
        
        // domain method - from Library.java
        this.state.getLibrary().addBook(book);

        return toBookResponse(book);
    }

    public BookResponse getBookByIsbn(String isbn) {
        Book book = this.state.getLibrary().getBookByIsbn(isbn);
        return book == null ? null : toBookResponse(book);
    }

    // public BookResponse searchByTitle(String title){
    //     Book book = this.state.getLibrary().getBookByName(title);
    //     return book == null ? null : toBookResponse(book);
    // }

    public List<BookResponse> searchByTitle(String title) {
        if (title == null) title = "";
        String lower = title.toLowerCase();
        return state.getLibrary().getAllBooks().stream()
                .filter(b -> b.getName().toLowerCase().contains(lower))
                .map(this::toBookResponse)
                .toList();
    }

    public List<BookResponse> getAllBooks() {
        return state.getLibrary().getAllBooks().stream()
        .map(this::toBookResponse)
        .toList();
    }

    private BookResponse toBookResponse(Book book) {
        return new BookResponse(
            book.getIsbn(),
            book.getName(),
            book.getAuthor(),
            book.getQuantity(),
            book.getQuantity()
        );
    }  
}
