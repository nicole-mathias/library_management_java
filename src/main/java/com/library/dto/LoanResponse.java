package com.library.dto;

import java.time.LocalDate;

public record LoanResponse (
    int loanId,
    String isbn,
    String bookTitle,
    String memberId,
    LocalDate issueDate,
    LocalDate dueDate,
    Double fineOnReturn
) {}
