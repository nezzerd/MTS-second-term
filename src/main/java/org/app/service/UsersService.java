package org.app.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.app.dto.request.UserPatchRequest;
import org.app.dto.request.UserRequest;
import org.app.dto.response.UserResponse;
import org.app.entity.University;
import org.app.entity.User;
import org.app.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Slf4j
@Service
public class UsersService {
  private final UsersRepository usersRepository;

  public UserResponse createUser(@Valid @NonNull UserRequest userRequest) {
    User user = new User();
    user.setName(userRequest.getName());
    user.setUniversity(userRequest.getUniversity());
    user.setCourses(userRequest.getCourses());
    user.setBooks(userRequest.getBooks());

    if (usersRepository.getUserByName(user.getName()) == null) {
      usersRepository.saveUser(user);
      log.info("Created new user {} ", userRequest.getName());
      UserResponse userResponse = new UserResponse();
      userResponse.setName(userRequest.getName());
      userResponse.setBooks(userRequest.getBooks());
      userResponse.setCourses(userRequest.getCourses());
      userResponse.setUniversity(userRequest.getUniversity());
      userResponse.setId(user.getId());
      return userResponse;
    } else {
      log.error("User with name {} already exists", userRequest.getName());
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User with name " + userRequest.getName() + " already exists");
    }
  }

  public UserResponse deleteUser(Integer id) {
    User user = usersRepository.getUserById(id);

    if (user != null) {
      usersRepository.deleteUser(id);
      log.info("Deleted user with id {}", id);
      UserResponse userResponse = new UserResponse();
      userResponse.setName(user.getName());
      userResponse.setBooks(user.getBooks());
      userResponse.setCourses(user.getCourses());
      userResponse.setUniversity(user.getUniversity());
      userResponse.setId(id);
      return userResponse;
    } else {
      log.error("User with id {} doesn't exist", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " doesn't exists");
    }
  }

  public UserResponse updateUserUniversity(@NonNull @Positive Integer userId, @Valid @NonNull UserPatchRequest userRequest) {
    User user = usersRepository.getUserById(userId);
    if (user != null) {
      University university = new University();
      university.setName(userRequest.getUniversity().getName());
      university.setCity(userRequest.getUniversity().getCity());
      user.setUniversity(university);
      usersRepository.updateUser(user);
      log.info("Updated user with new university {}", userRequest.getUniversity().getName());
      UserResponse userResponse = new UserResponse();
      userResponse.setId(userId);
      userResponse.setName(user.getName());
      userResponse.setUniversity(userRequest.getUniversity());
      return userResponse;
    } else {
      log.error("User with id {} doesnt exist", userId);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " doesn't exists");
    }
  }
}
