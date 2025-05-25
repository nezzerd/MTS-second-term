package org.app.repository;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Slf4j
@Repository
public class BooksRepository implements BooksRepositoryInterface {
  private final List<Book> books = new CopyOnWriteArrayList<>();
  private static final AtomicInteger idCounter = new AtomicInteger(1);

  @Override
  public Book saveBook(Book book) {
    log.info("Creating new book: {}", book);
    book.setId(idCounter.getAndIncrement());
    books.add(book);
    return book;
  }

  @Override
  public Book updateBook(Book updatedBook) {
    log.info("Updating book: {}", updatedBook);
    Book oldBook = getBookById(updatedBook.getId());
    books.set(books.indexOf(oldBook), updatedBook);
    return updatedBook;
  }

  @Override
  public Book getBookById(Integer id) {
    log.info("Retrieving book by id: {}", id);
    return books.stream()
        .filter(book -> book.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Book getBookByTitleAndAuthor(String title, String author) {
    log.info("Retrieving book by title and author: {}, {}", title, author);
    return books.stream()
        .filter(book -> book.getTitle().equals(title) && book.getAuthor().equals(author))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Book deleteBook(Integer id) {
    log.info("Deleting book with id: {}", id);
    Book book = getBookById(id);
    if (book != null) {
      books.remove(book);
    }
    return book;
  }

  @Override
  public List<Book> getAllBooks() {
    log.info("Retrieving all books");
    return List.copyOf(books);
  }
}
