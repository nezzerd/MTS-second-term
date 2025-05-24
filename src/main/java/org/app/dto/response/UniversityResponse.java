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
@Schema(description = "University's responce data")
public class UniversityResponse {
  @NonNull
  @Positive
  @Schema(description = "University's id", example = "52")
  private Integer id;

  @Schema(description = "University's name", example = "MIPT")
  @NotBlank
  private String name;

  @Schema(description = "University's city location", example = "Moscow")
  @NotBlank
  private String city;
}
