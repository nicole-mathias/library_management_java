package com.library.dto;

import jakarta.validation.constraints.Positive;

// what do you need in order to return a book
// member-id, book-isbn
// for now, based on the domain - each user has a member ID.
// Each member has a Data structure (dict: {isbn: book}) which store all the books borrowed.
// But for the future - if you want to move away from the design where each user has to store a 
// data structure (which can get unusually large based on the use case), you can resport to storing
// loanId's and having a seperate Entity for bookedLoaned which can be mapped to userID's (mapping structure
// will work great for a DB as well)

// So: as things stand, loanId has no effect unless some service/controller code looks it up and then calls different domain methods. 
// Your domain itself ignores it.

// so what happens if loanId is not provided?
// loanId is not set to @NotNull - so jackson sets loanId as null. Bean validation with @Positive doesnt fail with null

// For now I am using the member --> {isbn: book obj}

public record ReturnRequest (
    @Positive Integer loanId,
    Integer memberId,
    String isbn 
) {
    // this only used memberID and isbn (loanId can be used in the future to make code more useable for a DB)
    public boolean isMemberAndIsbnBased() {
        return memberId != null && isbn != null && !isbn.isBlank();
    } 
}
