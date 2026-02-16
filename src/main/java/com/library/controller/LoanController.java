package com.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BorrowRequest;
import com.library.dto.LoanResponse;
import com.library.dto.ReturnRequest;
import com.library.service.LoanService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;
    
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<LoanResponse> borrow(@Valid @RequestBody BorrowRequest request) {
        LoanResponse loan = this.loanService.borrow(request);

        if (loan == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(loan);
    }
    
    @PostMapping("/return")
    public ResponseEntity<LoanResponse> returnBook(@Valid @RequestBody ReturnRequest request) {
        LoanResponse loan = this.loanService.returnBook(request);
        if (loan == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(loan);
    }

    @GetMapping
    public List<LoanResponse> listLoans() {
        return this.loanService.getAllLoans();
    }  
}
