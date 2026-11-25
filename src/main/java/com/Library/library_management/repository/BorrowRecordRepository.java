package com.Library.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.library_management.entity.BorrowRecord;
import com.Library.library_management.entity.BorrowStatus;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
	boolean existsByBook_IdAndStatus(
            Long bookId,
            BorrowStatus status
    );
}
