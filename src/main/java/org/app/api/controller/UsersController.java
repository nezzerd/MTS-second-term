package org.app.api.controller;

import lombok.RequiredArgsConstructor;
import org.app.api.annotation.UsersAnnotation;
import org.app.dto.request.UserPatchRequest;
import org.app.dto.request.UserRequest;
import org.app.dto.response.UserResponse;
import org.app.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController implements UsersAnnotation {
  private final UsersService usersService;

  @Override
  public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
    UserResponse userResponse = usersService.createUser(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }

  @Override
  public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer id) {
    UserResponse userResponse = usersService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body(userResponse);
  }

  @Override
  public ResponseEntity<UserResponse> updateUniversity(@PathVariable Integer id, @RequestBody UserPatchRequest userRequest) {
    UserResponse userResponse = usersService.updateUserUniversity(id, userRequest);
    return ResponseEntity.status(HttpStatus.OK).body(userResponse);
  }
}
