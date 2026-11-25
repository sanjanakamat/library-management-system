package com.Library.library_management.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "member_id", nullable = false)
	    private Member member;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "book_id", nullable = false)
	    private Book book;

	    private LocalDate borrowDate;

	    private LocalDate dueDate;

	    private LocalDate returnDate;

	    @Enumerated(EnumType.STRING)
	    private BorrowStatus status;

	    public BorrowRecord() {
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Member getMember() {
			return member;
		}

		public void setMember(Member member) {
			this.member = member;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public LocalDate getBorrowDate() {
			return borrowDate;
		}

		public void setBorrowDate(LocalDate borrowDate) {
			this.borrowDate = borrowDate;
		}

		public LocalDate getDueDate() {
			return dueDate;
		}

		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}

		public LocalDate getReturnDate() {
			return returnDate;
		}

		public void setReturnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
		}

		public BorrowStatus getStatus() {
			return status;
		}

		public void setStatus(BorrowStatus status) {
			this.status = status;
		}


}
