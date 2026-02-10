package com.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateMemberRequest (
    @NotBlank String name,
    @Pattern(regexp = "STUDENT | STAFF", message = "type must be STUDENT or STAFF") String type
) {}
