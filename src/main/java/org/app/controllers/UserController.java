package org.app.controllers;

import org.app.entities.User;
import org.app.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/search")
    public User getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }

    @DeleteMapping("/delete")
    public boolean deleteUserByName(@RequestParam String name) {
        return userService.deleteUserByName(name);
    }
}
