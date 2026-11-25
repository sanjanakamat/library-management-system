package com.Library.library_management.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Library.library_management.dto.BookRequest;
import com.Library.library_management.dto.BookResponse;
import com.Library.library_management.entity.Book;
import com.Library.library_management.entity.BorrowStatus;
import com.Library.library_management.exception.ResourceNotFoundException;
import com.Library.library_management.repository.BookRepository;
import com.Library.library_management.repository.BorrowRecordRepository;
@Service
public class BookService {
	private final BookRepository bookRepository;
	private final BorrowRecordRepository borrowRecordRepository;
	public BookService(
	        BookRepository bookRepository,
	        BorrowRecordRepository borrowRecordRepository) {

	    this.bookRepository = bookRepository;
	    this.borrowRecordRepository = borrowRecordRepository;
	}
	private BookResponse mapToResponse(Book book) {

	    return new BookResponse(
	        book.getId(),
	        book.getTitle(),
	        book.getAuthor(),
	        book.getIsbn(),
	        book.getTotalCopies(),
	        book.getAvailableCopies()
	    );
	}
    public BookResponse addBook(BookRequest request) {
    	if(bookRepository.existsByIsbn(request.getIsbn())) {
    		throw new IllegalArgumentException(
    	            "Book with this ISBN already exists"
    	    );
    	}
    	 Book book = new Book();

    	    book.setTitle(request.getTitle());
    	    book.setAuthor(request.getAuthor());
    	    book.setIsbn(request.getIsbn());
    	    book.setTotalCopies(request.getTotalCopies());

    	    // Initially, all copies are available
    	    book.setAvailableCopies(request.getTotalCopies());

    	    Book savedBook = bookRepository.save(book);

    	    return mapToResponse(savedBook);
    }

    public  Page<BookResponse> getAllBooks(Pageable pageable) {
    	 return bookRepository.findAll(pageable)
    	            .map(this::mapToResponse);
    	            
    }
    
    public BookResponse getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Book not found with id: " + id
                    ));

        return mapToResponse(book);
    }
    public BookResponse updateBook(Long id, BookRequest request) {

        Book existingBook = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with id."+id));
        if(bookRepository.existsByIsbnAndIdNot(request.getIsbn(),id)) {
        	throw new IllegalArgumentException("Another book with this ISBN already exists");
        }
        int borrowedCopies=existingBook.getTotalCopies()-existingBook.getAvailableCopies();
        if(request.getTotalCopies()<borrowedCopies) {
        	 throw new IllegalArgumentException(
                     "Total copies cannot be less than currently borrowed copies: "
                     + borrowedCopies);
        }

        existingBook.setTitle(request.getTitle());
        existingBook.setAuthor(request.getAuthor());
        existingBook.setIsbn(request.getIsbn());

        existingBook.setTotalCopies(request.getTotalCopies());

        existingBook.setAvailableCopies(
                request.getTotalCopies() - borrowedCopies
        );

        Book updatedBook = bookRepository.save(existingBook);

        return mapToResponse(updatedBook);
    }
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        boolean hasActiveBorrowings =
                borrowRecordRepository.existsByBook_IdAndStatus(
                        id,
                        BorrowStatus.BORROWED
                );

        if (hasActiveBorrowings) {
            throw new IllegalArgumentException(
                    "Cannot delete book because it has active borrowings"
            );
        }

        bookRepository.delete(book);
    }
    public Page<BookResponse> searchBooks(String title,String author,Pageable pageable){
    	Page<Book> books;
    	if(title!=null && !title.isBlank() && author!=null && !author.isBlank()) {
    		 books = bookRepository
    	                .findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(
    	                        title, author, pageable);
    	}else if((title != null && !title.isBlank()) ){

            books = bookRepository
                    .findByTitleContainingIgnoreCase(title, pageable);

        }
    	 else {

    	        // Neither title nor author is provided
    	        books = bookRepository.findAll(pageable);
    	    }

    	    return books.map(this::mapToResponse);
    }
}
