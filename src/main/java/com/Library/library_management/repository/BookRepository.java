package com.Library.library_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.library_management.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>  {
	boolean existsByIsbn(String isbn);
	boolean existsByIsbnAndIdNot(String isbn,long id);
	Page<Book> findByTitleContainingIgnoreCase(
	        String title, Pageable pageable);

	Page<Book> findByAuthorContainingIgnoreCase(
	        String author, Pageable pageable);

	Page<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(
	        String title, String author, Pageable pageable);
	//containing because if book is james clerk, it should be found by james itself
}
