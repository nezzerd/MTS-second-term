package org.app.repositories;

import org.app.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        AtomicInteger idCounter = new AtomicInteger(1);
        users.add(new User(idCounter.getAndIncrement(), "test1"));
        users.add(new User(idCounter.getAndIncrement(), "test2"));
        users.add(new User(idCounter.getAndIncrement(), "test3"));
    }

    public List<User> findAll() {
        return users;
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.name().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteByName(String name) {
        User user = findByName(name);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }
}
