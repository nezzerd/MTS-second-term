package org.app.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Book's responce data")
public class BookResponse {
  @NonNull
  @Positive
  @Schema(description = "Book's id", example = "52")
  private Integer id;

  @Schema(description = "Book's title", example = "For Whom the Bell Tolls")
  @NotBlank
  private String title;

  @Schema(description = "Book's author", example = "Ernest Hemingway")
  @NotBlank
  private String author;
}
