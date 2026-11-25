package com.Library.library_management.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.library_management.dto.BookRequest;
import com.Library.library_management.dto.BookResponse;
import com.Library.library_management.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public BookResponse addBook(@Valid @RequestBody BookRequest request) {
    	return bookService.addBook(request);
    }
    @GetMapping
    public Page<BookResponse> getAllBooks(
    		@PageableDefault(
    				size=10,
    				sort="id",
    				direction=Sort.Direction.ASC)Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {

        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

}
