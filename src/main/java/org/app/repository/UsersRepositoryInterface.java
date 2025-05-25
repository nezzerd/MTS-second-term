package org.app.repository;

import org.app.entity.User;

import java.util.List;

public interface UsersRepositoryInterface {
  User saveUser(User user);

  User updateUser(User user);

  User getUserById(Integer id);

  User getUserByName(String name);

  User deleteUser(Integer id);

  List<User> getAllUsers();
}