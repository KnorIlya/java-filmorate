package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;
    int idCounter = 1;

    public UserService() {
        repository = new UserRepository();
    }

    public User createUser(User user) {
        populateId(user);
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        repository.getUsers().put(user.getId(), user);
        log.info("Create user");
        return user;
    }

    public User updateUser(User user) {
        Map<Integer, User> users = repository.getUsers();
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("Update user");
            return user;
        } else {
            throw new IllegalArgumentException("Incorrect user id");
        }
    }

    public List<User> getAllUser() {
        log.info("Get all users");
        return new ArrayList<>(repository.getUsers().values());
    }

    private void populateId(User user) {
        if (user.getId() == null) {
            user.setId(idCounter);
            idCounter++;
        }
    }
}
