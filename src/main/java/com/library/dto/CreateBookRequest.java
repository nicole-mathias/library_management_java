package com.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateBookRequest (
    @NotBlank String isbn,
    @NotBlank String title,
    @NotBlank String author, 
    @Positive int copies
) {}