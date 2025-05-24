package org.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Book's request data")
public class BookRequest {
  @Schema(description = "Book's title", example = "For Whom the Bell Tolls")
  @NotBlank
  private String title;

  @Schema(description = "Book's author", example = "Ernest Hemingway")
  @NotBlank
  private String author;
}
