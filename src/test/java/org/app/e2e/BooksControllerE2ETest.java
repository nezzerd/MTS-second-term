package org.app.e2e;

import org.app.entity.Book;
import org.app.repository.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksControllerE2ETest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private BooksRepository booksRepository;

  @BeforeEach
  void setup() {
    Book testBook = new Book();
    testBook.setTitle("Harry Potter");
    testBook.setAuthor("Joanne Rowling");
    booksRepository.saveBook(testBook);
  }

  @Test
  public void testGetAllBooks() {
    ResponseEntity<String> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/api/books",
        String.class
    );

    if (response.getStatusCode() != HttpStatus.OK) {
      throw new RuntimeException("Expected status 200 OK, but got: " + response.getStatusCode());
    }

    String responseBody = response.getBody();

    for (String value : Arrays.asList("id", "Harry Potter", "Joanne Rowling")) {
      if (!responseBody.contains(value)) {
        throw new RuntimeException("Response body does not contain required value: " + value);
      }
    }
  }
}