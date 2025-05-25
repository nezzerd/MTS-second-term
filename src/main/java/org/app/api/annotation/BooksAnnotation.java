package org.app.api.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.dto.request.BookPatchRequest;
import org.app.dto.request.BookRequest;
import org.app.dto.response.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Books API")
public interface BooksAnnotation {
  @Operation(summary = "Create a new book")
  @ApiResponse(
      responseCode = "201",
      description = "Book created successfully",
      content = @Content(schema = @Schema(implementation = BookResponse.class)))
  @ApiResponse(
      responseCode = "409",
      description = "Book already exists")
  @PostMapping
  ResponseEntity<BookResponse> createBook(
      @Parameter(description = "Book's data", required = true)
      @RequestBody BookRequest bookRequest);

  @Operation(summary = "Delete book by ID")
  @ApiResponse(
      responseCode = "200",
      description = "Book deleted successfully",
      content = @Content(schema = @Schema(implementation = BookResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Book not found",
      content = @Content)
  @DeleteMapping("/{id}")
  ResponseEntity<BookResponse> deleteBook(
      @Parameter(description = "Book ID", required = true)
      @PathVariable Integer id);

  @Operation(summary = "Get all books")
  @ApiResponse(
      responseCode = "200",
      description = "Books retrieved successfully",
      content = @Content(schema = @Schema(implementation = BookResponse.class)))
  @GetMapping
  ResponseEntity<List<BookResponse>> getAllBooks();

  @Operation(summary = "Full update book")
  @ApiResponse(
      responseCode = "200",
      description = "Book updated successfully",
      content = @Content(schema = @Schema(implementation = BookResponse.class)))
  @ApiResponse(responseCode = "404", description = "Book not found")
  @PutMapping("/{id}")
  ResponseEntity<BookResponse> fullUpdateBook(
      @Parameter(description = "Book ID", required = true)
      @PathVariable Integer id,
      @RequestBody BookRequest bookRequest);

  @Operation(summary = "Partial update book title")
  @ApiResponse(
      responseCode = "200",
      description = "Book title updated",
      content = @Content(schema = @Schema(implementation = BookResponse.class)))
  @ApiResponse(responseCode = "404", description = "Book not found")
  @PatchMapping("/{id}")
  ResponseEntity<BookResponse> updateTitle(
      @Parameter(description = "Book ID", required = true)
      @PathVariable Integer id,
      @RequestBody BookPatchRequest bookRequest);

  @Operation(summary = "Get book by ID")
  @ApiResponse(
      responseCode = "200",
      description = "Book details",
      content = @Content(schema = @Schema(implementation = BookResponse.class)))
  @ApiResponse(responseCode = "404", description = "Book not found")
  @GetMapping("/{id}")
  ResponseEntity<BookResponse> getBookById(
      @Parameter(description = "Book ID", required = true)
      @PathVariable Integer id);
}
