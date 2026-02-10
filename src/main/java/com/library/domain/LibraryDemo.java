package com.library.domain;

// Design a library management system to manage books, members, borrowing, returns, and late fees.

// do we need locking in the Library system - probably yes, if there are multiple checkout counters,
// then having locking would be essential.
// when someone searches for a book via get Book - the person should have a phsysical copy
// of the book in their hand (i.e which means a book object and the book quantity reduces by 1)
// so - either the person needs to checkout their book or put it back.
// So returnBook should probably --> check if the book was actually borred else just simply return it back -->
// both the cases will add +1 quantity

// During checkout - verify if the book is still available and only then assign it to a member
// So how do we ensure that the first person who ran the command get
public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books to catalog
        library.addBook(new Book("001", "Clean Code", "Robert Martin", 3));
        library.addBook(new Book("002", "Design Patterns", "Gang of Four", 2));
        library.addBook(new Book("003", "The Pragmatic Programmer", "Hunt & Thomas", 1));

        // Create members
        Member student = new Student("S1", "Alice");
        Member staff = new Staff("F1", "Bob");

        // Look up a book by name and by ISSN
        Book cleanCode = library.getBookByName("Clean Code");
        Book designPatterns = library.getBookByIssn("002");

        System.out.println("--- Borrowing ---");
        library.borrowBook(student, cleanCode);
        library.borrowBook(student, designPatterns);
        library.borrowBook(staff, library.getBookByName("The Pragmatic Programmer"));

        // Try to borrow a book not in catalog (no-op)
        library.borrowBook(student, new Book("999", "Unknown Book", "Nobody", 1));

        System.out.println("\n--- Returning ---");
        library.returnBook(student, cleanCode);
        library.returnBook(student, designPatterns);
        library.returnBook(staff, library.getBookByIssn("003"));

        // Fees (0 if returned within due days; would be > 0 if late)
        System.out.println("\n--- Fees ---");
        System.out.println("Student pending fees: $" + student.getPendingFees());
        System.out.println("Staff pending fees: $" + staff.getPendingFees());
        student.payFees(student.getPendingFees());
    }
}