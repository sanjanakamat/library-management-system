package com.Library.library_management.dto;

import java.time.LocalDate;

import com.Library.library_management.entity.BorrowStatus;



public class BorrowRecordResponse {
	private Long id;

    private Long memberId;
    private String memberName;

    private Long bookId;
    private String bookTitle;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private BorrowStatus status;

    public BorrowRecordResponse(
            Long id,
            Long memberId,
            String memberName,
            Long bookId,
            String bookTitle,
            LocalDate borrowDate,
            LocalDate dueDate,
            LocalDate returnDate,
            BorrowStatus status) {

        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }
}
