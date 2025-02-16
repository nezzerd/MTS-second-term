package org.app.controllers;

import org.app.entities.Book;
import org.app.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/search")
    public Book getBookByTitle(@RequestParam String title) {
        return bookService.getBookByTitle(title);
    }

    @DeleteMapping("/delete")
    public boolean deleteUserByName(@RequestParam String title) {
        return bookService.deleteBookByTitle(title);
    }

    @GetMapping("/by-author")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }
}
