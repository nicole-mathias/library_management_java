package com.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookResponse;
import com.library.dto.CreateBookRequest;
import com.library.service.BookService;

import jakarta.validation.Valid;

/**
 * REST controller: maps HTTP verbs and paths to service calls.
 * Stays thin — no business logic, only validation and status codes.
 */

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody CreateBookRequest request) {
        BookResponse created = this.bookService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<BookResponse> listBooks() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponse> getByIsbn(@PathVariable String isbn) {
        BookResponse book = this.bookService.getBookByIsbn(isbn);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/search")
    public List<BookResponse> searchByTitle(@RequestParam(required = false, defaultValue = "") String title) {
        return this.bookService.searchByTitle(title);
    }   
}
