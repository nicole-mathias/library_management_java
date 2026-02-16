package com.library.domain;

public class Staff extends Member{
    private static final int BOOKS_ALLOWED_COUNT = 10;
    private static final int DAYS_ALLOWED = 30;

    public Staff(int id, String name){
        super(id, MemberType.StaffMember, name, BOOKS_ALLOWED_COUNT, DAYS_ALLOWED);
    }
}
