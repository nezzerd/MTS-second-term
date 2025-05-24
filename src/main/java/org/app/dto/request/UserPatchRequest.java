package org.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.app.entity.University;

@Getter
@AllArgsConstructor
@Schema(description = "User's data for patch query")
public class UserPatchRequest {
  @Schema(
      description = "User's university",
      example = """
          {
            "name": "MIPT",
            "city": "Moscow"
          }"""
  )
  @NonNull
  @Valid
  private University university;
}

