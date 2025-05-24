package org.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Course's request data")
public class CourseRequest {
  @Schema(description = "Course's name", example = "C++")
  @NotBlank
  private String name;
}
