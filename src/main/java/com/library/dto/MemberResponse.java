package com.library.dto;

public record MemberResponse (Integer id, String name, String type, int activeLoans) {}
