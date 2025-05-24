package org.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "University's request data")
public class UniversityRequest {
  @Schema(description = "University's name", example = "MIPT")
  @NotBlank
  private String name;

  @Schema(description = "University's city location", example = "Moscow")
  @NotBlank
  private String city;
}
