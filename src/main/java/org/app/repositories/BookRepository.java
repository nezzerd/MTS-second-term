package org.app.repositories;

import org.app.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        AtomicInteger idCounter = new AtomicInteger(1);
        books.add(new Book(idCounter.getAndIncrement(), "book1", "author1"));
        books.add(new Book(idCounter.getAndIncrement(), "book2", "author2"));
        books.add(new Book(idCounter.getAndIncrement(), "book3", "author2"));
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findByTitle(String title) {
        for (Book book : books) {
            if (book.title().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean deleteByTitle(String title) {
        Book book = findByTitle(title);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.author().equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
}
