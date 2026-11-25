package com.Library.library_management.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.library_management.dto.BorrowRecordResponse;
import com.Library.library_management.service.BorrowRecordService;

@RestController
@RequestMapping("/api/borrow")
public class BorrowRecordController {
	private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(
            BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("/member/{memberId}/book/{bookId}")
    public BorrowRecordResponse borrowBook(
            @PathVariable Long memberId,
            @PathVariable Long bookId) {

        return borrowRecordService.borrowBook(memberId, bookId);
    }

    @PutMapping("/return/{recordId}")
    public BorrowRecordResponse returnBook(
            @PathVariable Long recordId) {

        return borrowRecordService.returnBook(recordId);
    }
}
