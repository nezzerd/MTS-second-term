package org.app.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.app.entity.Book;
import org.app.entity.Course;
import org.app.entity.University;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User's responce data")
public class UserResponse {
  @NonNull
  @Positive
  @Schema(description = "User's id", example = "52")
  private Integer id;

  @Schema(description = "User's full name", example = "Alexey Maleev")
  @NotBlank
  private String name;

  @Schema(
      description = "User's university",
      nullable = true,
      example = """
          {
            "name": "MIPT",
            "city": "Moscow"
          }"""
  )
  @Nullable
  @Valid
  private University university;

  @Schema(
      description = "List of user's courses",
      nullable = true,
      example = """
          [
              {
                "name": "C++"
              },
              {
                "name": "Java"
              }
          ]"""
  )
  @Nullable
  @Valid
  private Set<Course> courses;

  @Schema(
      description = "List of user's books",
      nullable = true,
      example = """
          [
              {
                "name": "Harry Potter"
              },
              {
                "name": "Effective Java"
              }
          ]""")
  @Nullable
  @Valid
  private Set<Book> books;
}
