package org.app.api.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.dto.request.CourseRequest;
import org.app.dto.response.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Courses API")
public interface CoursesAnnotation {
  @Operation(summary = "Create a new course")
  @ApiResponse(
      responseCode = "201",
      description = "Course created successfully",
      content = @Content(schema = @Schema(implementation = CourseResponse.class)))
  @ApiResponse(
      responseCode = "409",
      description = "Course already exists")
  @PostMapping()
  ResponseEntity<CourseResponse> createCourse(
      @Parameter(description = "Course's data", required = true)
      @RequestBody CourseRequest courseRequest);

  @Operation(summary = "Delete course by ID")
  @ApiResponse(
      responseCode = "200",
      description = "Course deleted successfully",
      content = @Content(schema = @Schema(implementation = CourseResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Course not found",
      content = @Content)
  @DeleteMapping("/{id}")
  ResponseEntity<CourseResponse> deleteCourse(
      @Parameter(description = "Course ID", required = true)
      @PathVariable Integer id);

  @Operation(summary = "Get all courses")
  @ApiResponse(
      responseCode = "200",
      description = "Courses retrieved successfully",
      content = @Content(schema = @Schema(implementation = CourseResponse.class)))
  @GetMapping()
  ResponseEntity<List<CourseResponse>> getAllCourses();

  @Operation(summary = "Full update course")
  @ApiResponse(
      responseCode = "200",
      description = "Course updated successfully",
      content = @Content(schema = @Schema(implementation = CourseResponse.class)))
  @ApiResponse(responseCode = "404", description = "Course not found")
  @PutMapping("/{id}")
  ResponseEntity<CourseResponse> fullUpdateCourse(
      @Parameter(description = "Course ID", required = true)
      @PathVariable Integer id,
      @RequestBody CourseRequest courseRequest);

  @Operation(summary = "Get course by ID")
  @ApiResponse(
      responseCode = "200",
      description = "Course details",
      content = @Content(schema = @Schema(implementation = CourseResponse.class)))
  @ApiResponse(responseCode = "404", description = "Course not found")
  @GetMapping("/{id}")
  ResponseEntity<CourseResponse> getCourseById(
      @Parameter(description = "Course ID", required = true)
      @PathVariable Integer id);
}