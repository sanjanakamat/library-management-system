package com.Library.library_management.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.Library.library_management.dto.BorrowRecordResponse;
import com.Library.library_management.entity.Book;
import com.Library.library_management.entity.BorrowRecord;
import com.Library.library_management.entity.BorrowStatus;
import com.Library.library_management.entity.Member;
import com.Library.library_management.exception.ResourceNotFoundException;
import com.Library.library_management.repository.BookRepository;
import com.Library.library_management.repository.BorrowRecordRepository;
import com.Library.library_management.repository.MemberRepository;

import jakarta.transaction.Transactional;
@Service
public class BorrowRecordService {
	private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordService(
            BookRepository bookRepository,
            MemberRepository memberRepository,
            BorrowRecordRepository borrowRecordRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }
    @Transactional
    public BorrowRecordResponse borrowBook(Long memberId, Long bookId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Member not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalArgumentException(
                "No copies available for borrowing");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        BorrowRecord record = new BorrowRecord();

        record.setMember(member);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        record.setStatus(BorrowStatus.BORROWED);

        BorrowRecord savedRecord = borrowRecordRepository.save(record);

        return mapToResponse(savedRecord);
    }
    
    @Transactional
    public BorrowRecordResponse returnBook(Long recordId) {

        BorrowRecord record = borrowRecordRepository.findById(recordId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Borrow record not found"));

        if (record.getStatus() == BorrowStatus.RETURNED) {
            throw new IllegalArgumentException(
                "Book has already been returned");
        }

        Book book = record.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowStatus.RETURNED);

        BorrowRecord updatedRecord= borrowRecordRepository.save(record);
        return mapToResponse(updatedRecord);
    }
    
    private BorrowRecordResponse mapToResponse(BorrowRecord record) {

        return new BorrowRecordResponse(
                record.getId(),
                record.getMember().getId(),
                record.getMember().getName(),
                record.getBook().getId(),
                record.getBook().getTitle(),
                record.getBorrowDate(),
                record.getDueDate(),
                record.getReturnDate(),
                record.getStatus()
        );
    }

}
