package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private long idCounter = 1;
    private final Map<Long, User> storage = new HashMap<>();

    public Map<Long, User> getStorage() {
        return storage;
    }

    public void delete(Long id) {
        storage.remove(id);
    }

    public User create(User user) {
        populateId(user);
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        if (user.getFriend() == null) {
            user.setFriend(new HashSet<>());
        }
        storage.put(user.getId(), user);
        log.info("Create user");
        return user;
    }

    public User update(User user) {
        Map<Long, User> users = storage;
        if (users.containsKey(user.getId())) {
            user.setFriend(users.get(user.getId()).getFriend());
            users.put(user.getId(), user);
            log.info("Update user");
            return user;
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public List<User> getAll() {
        log.info("Get all users");
        return new ArrayList<>(storage.values());
    }

    public User getById(Long id) {
        if (!storage.containsKey(id)) {
            throw new NotFoundException("User not found");
        }
        return storage.get(id);
    }

    private void populateId(User user) {
        if (user.getId() == null) {
            user.setId(idCounter);
            idCounter++;
        }
    }
}
