package com.library.domain;

public class Student extends Member{
    private static final int BOOKS_ALLOWED_COUNT = 5;
    private static final int DAYS_ALLOWED = 15;

    public Student(int id, String name){
        super(id, MemberType.StudentMember, name, BOOKS_ALLOWED_COUNT, DAYS_ALLOWED);
    }
}