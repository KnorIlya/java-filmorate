package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserDao {
    User findUserById(Long id);

    List<User> getAllFriends(Long id);

    User create(User user);

    User update(User user);

    void delete(Long id);

    List<User> getAll();

    List<User> getMutualFriends(Long userId, Long friendId);

    void addFriend(Long userId, Long friendId);

    List<User> getAllSubscriptions(Long id);

    List<User> getMutualSubscriptions(Long userId, Long friendId);

    void removeFriend(Long userId, Long friendId);
}
