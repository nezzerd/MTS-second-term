package org.app.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.app.dto.request.BookPatchRequest;
import org.app.dto.request.BookRequest;
import org.app.dto.response.BookResponse;
import org.app.entity.Book;
import org.app.repository.BooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class BooksService {
  private final BooksRepository booksRepository;

  public BookResponse createBook(@Valid @NonNull BookRequest bookRequest) {
    Book book = new Book();
    book.setTitle(bookRequest.getTitle());
    book.setAuthor(bookRequest.getAuthor());

    if (booksRepository.getBookByTitleAndAuthor(book.getTitle(), book.getAuthor()) == null) {
      booksRepository.saveBook(book);
      log.info("Created new book {} by {}", bookRequest.getTitle(), bookRequest.getAuthor());
      BookResponse bookResponse = new BookResponse();
      bookResponse.setAuthor(bookRequest.getAuthor());
      bookResponse.setTitle(bookRequest.getTitle());
      bookResponse.setId(book.getId());
      return bookResponse;
    } else {
      log.error("Book with title {} and author {} already exists", bookRequest.getTitle(), bookRequest.getAuthor());
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Book already exists");
    }
  }

  public BookResponse deleteBook(Integer id) {
    Book book = booksRepository.getBookById(id);

    if (book != null) {
      booksRepository.deleteBook(id);
      log.info("Deleted book with id {}", id);
      BookResponse bookResponse = new BookResponse();
      bookResponse.setAuthor(book.getAuthor());
      bookResponse.setTitle(book.getTitle());
      bookResponse.setId(id);
      return bookResponse;
    } else {
      log.error("Book with id {} not found", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
    }
  }

  public List<BookResponse> getAllBooks() {
    log.info("Retrieving all books");
    List<Book> books = booksRepository.getAllBooks();

    return books.stream()
        .map(book -> new BookResponse(
            book.getId(),
            book.getTitle(),
            book.getAuthor()
        ))
        .collect(Collectors.toList());
  }

  public BookResponse updateBook(@NonNull @Positive Integer id, @Valid @NonNull BookRequest bookRequest) {
    Book book = booksRepository.getBookById(id);
    if (book != null) {
      book.setTitle(bookRequest.getTitle());
      book.setAuthor(bookRequest.getAuthor());
      booksRepository.updateBook(book);
      log.info("Updated book {} by {}", bookRequest.getTitle(), bookRequest.getAuthor());
      BookResponse bookResponse = new BookResponse();
      bookResponse.setAuthor(bookRequest.getAuthor());
      bookResponse.setTitle(bookRequest.getTitle());
      bookResponse.setId(id);
      return bookResponse;
    } else {
      log.error("Book with id {} doesnt exist", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
    }
  }

  public BookResponse updateTitle(@NonNull @Positive Integer id, @Valid @NonNull BookPatchRequest bookRequest) {
    Book book = booksRepository.getBookById(id);
    if (book != null) {
      book.setTitle(bookRequest.getTitle());
      booksRepository.updateBook(book);
      log.info("Updated book with new title {}", bookRequest.getTitle());
      BookResponse bookResponse = new BookResponse();
      bookResponse.setTitle(bookRequest.getTitle());
      bookResponse.setAuthor(book.getAuthor());
      bookResponse.setId(id);
      return bookResponse;
    } else {
      log.error("Book with id {} doesnt exist", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
    }
  }

  public BookResponse getBookById(@NonNull @Positive Integer id) {
    Book book = booksRepository.getBookById(id);
    if (book != null) {
      BookResponse bookResponse = new BookResponse();
      bookResponse.setTitle(book.getTitle());
      bookResponse.setAuthor(book.getAuthor());
      bookResponse.setId(id);
      return bookResponse;
    } else {
      log.error("Book with id {} doesnt exist", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
    }
  }
}
