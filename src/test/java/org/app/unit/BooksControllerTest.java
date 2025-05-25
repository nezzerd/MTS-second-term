package org.app.unit;


import org.app.api.controller.BooksController;
import org.app.security.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.app.dto.response.BookResponse;
import org.app.service.BooksService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@WebMvcTest(BooksController.class)
public class BooksControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private BooksService booksService;

  @Test
  void getAllBooks_ReturnsBooksList() throws Exception {
    BookResponse book1 = new BookResponse(1, "Harry Potter", "Joanne Rowling");
    BookResponse book2 = new BookResponse(2, "Tom Sawyer", "Mark Twain");

    when(booksService.getAllBooks())
        .thenReturn(Arrays.asList(book1, book2));

    mockMvc.perform(get("/api/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].title").value("Harry Potter"))
        .andExpect(jsonPath("$[0].author").value("Joanne Rowling"))
        .andExpect(jsonPath("$[1].title").value("Tom Sawyer"))
        .andExpect(jsonPath("$[1].author").value("Mark Twain"));
  }

  @Test
  void getBookById_ValidId_ReturnsBook() throws Exception {
    BookResponse book = new BookResponse(1, "Harry Potter", "Joanne Rowling");
    when(booksService.getBookById(1))
        .thenReturn(book);

    mockMvc.perform(get("/api/books/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Harry Potter"))
        .andExpect(jsonPath("$.author").value("Joanne Rowling"))
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  void getBookById_InvalidId_ReturnsNotFound() throws Exception {
    when(booksService.getBookById(500))
        .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

    mockMvc.perform(get("/api/books/500"))
        .andExpect(status().isNotFound());
  }
}