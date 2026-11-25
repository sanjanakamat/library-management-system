package com.Library.library_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequest {
	 @NotBlank(message = "Title is required")
	    private String title;

	    @NotBlank(message = "Author is required")
	    private String author;

	    @NotBlank(message = "ISBN is required")
	    private String isbn;

	    @NotNull(message = "Total copies is required")
	    @Min(value = 1, message = "At least one copy is required")
	    private Integer totalCopies;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public Integer getTotalCopies() {
			return totalCopies;
		}

		public void setTotalCopies(Integer totalCopies) {
			this.totalCopies = totalCopies;
		}
	    
}
