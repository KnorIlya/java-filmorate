package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {
    User create(User user);

    User update(User user);

    List<User> getAll();

    User getById(Long id);

    Map<Long, User> getStorage();

    void delete(Long id);
}
