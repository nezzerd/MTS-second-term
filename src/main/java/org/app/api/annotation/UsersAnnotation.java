package org.app.api.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.dto.request.UserPatchRequest;
import org.app.dto.request.UserRequest;
import org.app.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Users API")
public interface UsersAnnotation {
  @Operation(summary = "Create a new user")
  @ApiResponse(
      responseCode = "201",
      description = "User created successfully",
      content = @Content(schema = @Schema(implementation = UserResponse.class))
  )
  @ApiResponse(
      responseCode = "409",
      description = "User already exists",
      content = @Content
  )
  @PostMapping()
  ResponseEntity<UserResponse> createUser(
      @Parameter(description = "User's data", required = true)
      @RequestBody UserRequest userRequest
  );

  @Operation(summary = "Delete user by ID")
  @ApiResponse(
      responseCode = "200",
      description = "User deleted successfully",
      content = @Content(schema = @Schema(implementation = UserResponse.class))
  )
  @ApiResponse(
      responseCode = "404",
      description = "User not found",
      content = @Content
  )
  @DeleteMapping("/{id}")
  ResponseEntity<UserResponse> deleteUser(
      @Parameter(description = "User ID", required = true)
      @PathVariable Integer id
  );

  @Operation(summary = "Partial update user university")
  @ApiResponse(
      responseCode = "200",
      description = "User updated successfully",
      content = @Content(schema = @Schema(implementation = UserResponse.class))
  )
  @ApiResponse(
      responseCode = "404",
      description = "User with such id doesn't exist"
  )
  @PatchMapping("/{id}")
  ResponseEntity<UserResponse> updateUniversity(
      @Parameter(description = "User ID", required = true)
      @PathVariable Integer id,
      @Parameter(description = "Updated user's title")
      @RequestBody UserPatchRequest userRequest);
}