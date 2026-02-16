package com.library.dto;

import java.time.LocalDate;

public record LoanResponse(
    Integer loanId,
    String isbn,
    String bookTitle,
    int memberId,
    LocalDate issueDate,
    LocalDate dueDate,
    Double fineOnReturn
) {}
