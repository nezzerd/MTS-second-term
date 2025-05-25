package org.app.repository;

import org.app.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepositoryInterface {
  Book saveBook(Book book);

  Book updateBook(Book book);

  Book deleteBook(Integer id);

  Book getBookById(Integer id);

  Book getBookByTitleAndAuthor(String title, String author);

  List<Book> getAllBooks();
}
