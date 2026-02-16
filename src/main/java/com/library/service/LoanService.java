package com.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.library.domain.Book;
import com.library.domain.BorrowBook;
import com.library.domain.Member;
import com.library.dto.BorrowRequest;
import com.library.dto.LoanResponse;
import com.library.dto.ReturnRequest;

@Service
public class LoanService {

    private final LibraryState state;

    public LoanService(LibraryState state) {
        this.state = state;
    }

    public LoanResponse borrow(BorrowRequest req) {
        Member member = this.state.getIdToMember().get(req.memberId());
        Book book = this.state.getLibrary().getBookByIsbn(req.isbn());

        if (member == null || book == null) 
            return null;

        if (!member.can_borrow())
            return null;

        this.state.getLibrary().borrowBook(member, book);

        LocalDate borrowedDate = LocalDate.now();

        return new LoanResponse(
            state.getNextLoanId().getAndIncrement(),
            book.getIsbn(),
            book.getName(),
            req.memberId(),
            borrowedDate,
            borrowedDate.plusDays(member.getDays_allowed()),
            null
        );
    }

    public LoanResponse returnBook(ReturnRequest req) {
        if (!req.isMemberAndIsbnBased()) 
            return null;

        Member member = state.getIdToMember().get(req.memberId());
        Book book = state.getLibrary().getBookByIsbn(req.isbn());
        if (member == null || book == null) return null;

        state.getLibrary().returnBook(member, book);
        return new LoanResponse(null, book.getIsbn(), book.getName(), req.memberId(), null, null, 0.0);
    }

    public List<LoanResponse> getAllLoans() {
        List<LoanResponse> out = new ArrayList<>();
        for (Map.Entry<Integer, Member> e : state.getIdToMember().entrySet()) {
            int memberId = e.getKey();
            Member member = e.getValue();
            for (BorrowBook bb : member.getBooks_borrowed().values()) {
                Book book = bb.getBook();
                out.add(new LoanResponse(
                        null,
                        book.getIsbn(),
                        book.getName(),
                        memberId,
                        bb.getBorrowedDate(),
                        bb.getBorrowedDate().plusDays(member.getDays_allowed()),
                        null
                ));
            }
        }
        return out;
    } 
}
