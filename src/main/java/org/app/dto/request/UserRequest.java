package org.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.app.entity.Book;
import org.app.entity.Course;
import org.app.entity.University;

import java.util.Set;

@Getter
@AllArgsConstructor
@Schema(description = "User's data")
public class UserRequest {
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
          ]"""
  )
  @Nullable
  @Valid
  private Set<Book> books;
}
