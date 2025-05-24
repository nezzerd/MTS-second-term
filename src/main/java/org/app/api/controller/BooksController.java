package org.app.api.controller;

import lombok.RequiredArgsConstructor;
import org.app.api.annotation.BooksAnnotation;
import org.app.dto.request.BookPatchRequest;
import org.app.dto.request.BookRequest;
import org.app.dto.response.BookResponse;
import org.app.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BooksController implements BooksAnnotation {
  private final BooksService booksService;

  @Override
  public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
    BookResponse response = booksService.createBook(bookRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Override
  public ResponseEntity<BookResponse> deleteBook(@PathVariable Integer id) {
    BookResponse response = booksService.deleteBook(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  public ResponseEntity<List<BookResponse>> getAllBooks() {
    return ResponseEntity.ok(booksService.getAllBooks());
  }

  @Override
  public ResponseEntity<BookResponse> fullUpdateBook(
      @PathVariable Integer id,
      @RequestBody BookRequest bookRequest) {
    return ResponseEntity.ok(booksService.updateBook(id, bookRequest));
  }

  @Override
  public ResponseEntity<BookResponse> updateTitle(
      @PathVariable Integer id,
      @RequestBody BookPatchRequest bookRequest) {
    return ResponseEntity.ok(booksService.updateTitle(id, bookRequest));
  }

  @Override
  public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
    return ResponseEntity.ok(booksService.getBookById(id));
  }
}
