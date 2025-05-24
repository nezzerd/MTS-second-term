package org.app.api.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.dto.request.UniversityRequest;
import org.app.dto.response.UniversityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Universities API")
public interface UniversitiesAnnotation {
  @Operation(summary = "Create a new university")
  @ApiResponse(
      responseCode = "201",
      description = "University created successfully",
      content = @Content(schema = @Schema(implementation = UniversityResponse.class))
  )
  @ApiResponse(
      responseCode = "409",
      description = "University already exists",
      content = @Content
  )
  @PostMapping()
  ResponseEntity<UniversityResponse> createUniversity(
      @Parameter(description = "University's name and city", required = true)
      @RequestBody UniversityRequest universityRequest
  );

  @Operation(summary = "Delete university by ID")
  @ApiResponse(
      responseCode = "200",
      description = "University deleted successfully",
      content = @Content(schema = @Schema(implementation = UniversityResponse.class))
  )
  @ApiResponse(
      responseCode = "404",
      description = "University does not exist",
      content = @Content
  )
  @DeleteMapping("/{id}")
  ResponseEntity<UniversityResponse> deleteUniversity(
      @Parameter(description = "University ID", required = true)
      @PathVariable Integer id
  );

  @Operation(summary = "Get all universities")
  @ApiResponse(
      responseCode = "200",
      description = "Universities retrieved successfully",
      content = @Content(schema = @Schema(implementation = UniversityResponse.class))
  )
  @GetMapping()
  ResponseEntity<List<UniversityResponse>> getAllUniversities();
}