package org.app.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Slf4j
@Repository
public class UsersRepository implements UsersRepositoryInterface {
  private final List<User> users = new CopyOnWriteArrayList<>();
  private static final AtomicInteger idCounter = new AtomicInteger(1);

  @Override
  public User saveUser(User user) {
    log.info("Creating new user: {}", user);
    user.setId(idCounter.getAndIncrement());
    users.add(user);
    return user;
  }

  @Override
  public User updateUser(User updatedUser) {
    log.info("Updating user: {}", updatedUser);
    User oldUser = getUserById(updatedUser.getId());
    if (oldUser != null) {
      users.set(users.indexOf(oldUser), updatedUser);
    }
    return oldUser;
  }

  @Override
  public User getUserById(Integer id) {
    log.info("Retrieving user by id: {}", id);
    return users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public User getUserByName(String name) {
    log.info("Retrieving user by name: {}", name);
    return users.stream()
        .filter(user -> user.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  @Override
  public User deleteUser(Integer id) {
    log.info("Deleting user with id: {}", id);
    User user = getUserById(id);
    if (user != null) {
      users.remove(user);
    }
    return user;
  }

  @Override
  public List<User> getAllUsers() {
    log.info("Retrieving all users");
    return List.copyOf(users);
  }
}
