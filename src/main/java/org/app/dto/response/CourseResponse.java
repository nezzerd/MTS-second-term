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
@Schema(description = "Course's response data")
public class CourseResponse {
  @NonNull
  @Positive
  @Schema(description = "Course's id", example = "52")
  private Integer id;

  @Schema(description = "Course's name", example = "C++")
  @NotBlank
  private String name;
}
