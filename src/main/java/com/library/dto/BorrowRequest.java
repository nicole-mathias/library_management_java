package com.library.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// needs member obj and book obj
public record BorrowRequest (
    @NotNull @Positive Integer memberId,
    @NotNull String isbn
) {}
