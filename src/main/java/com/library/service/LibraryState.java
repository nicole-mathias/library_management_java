package com.library.service;

import com.library.domain.Library;
import com.library.domain.Member;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Holds shared in-memory state for the library: books, members, and counters.
 * This allows multiple services to collaborate on the same data.
 */
@Component
public class LibraryState {

    private final Library library = new Library();
    private final Map<Integer, Member> idToMember = new ConcurrentHashMap<>();
    private final AtomicInteger nextMemberId = new AtomicInteger(1);
    private final AtomicInteger nextLoanId = new AtomicInteger(1);

    public Library getLibrary() {
        return library;
    }

    public Map<Integer, Member> getIdToMember() {
        return idToMember;
    }

    public AtomicInteger getNextMemberId() {
        return nextMemberId;
    }

    public AtomicInteger getNextLoanId() {
        return nextLoanId;
    }
}

