package ru.yandex.practicum.filmorate.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.UserDao;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.dao.SqlQueries.*;

@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    @Qualifier("insertForUsers")
    private final SimpleJdbcInsert insertForUsers;
    @Qualifier("insertForFriends")
    private final SimpleJdbcInsert insertForFriends;


    public UserDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert insertForUsers, SimpleJdbcInsert insertForFriends) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertForUsers = insertForUsers;
        this.insertForFriends = insertForFriends;
    }

    @Override
    public User findUserById(Long id) {
        return jdbcTemplate.queryForObject(FIND_USER_BY_ID, new UserMapper(), id);
    }

    @Override
    public List<User> getAllFriends(Long id) {
        return jdbcTemplate.query(GET_ALL_USER_FRIENDS, new UserMapper(), id, id);
    }

    @Override
    public List<User> getAllSubscriptions(Long id) {
        return jdbcTemplate.query(GET_ALL_USER_SUBSCRIPTIONS, new UserMapper(), id);
    }

    @Override
    public User create(User user) {
        Map<String, Object> parameters = new HashMap<>();
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("login", user.getLogin());
        parameters.put("birthday", user.getBirthday());
        Number newId = insertForUsers.executeAndReturnKey(parameters);
        user.setId((long) newId);
        return user;
    }

    @Override
    public User update(User user) {
        Object[] params = new Object[]{user.getName(), user.getEmail(), user.getLogin(), user.getBirthday(), user.getId()};
        if (jdbcTemplate.update(UPDATE_USER, params) == 0) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER_FROM_TABLE_FRIENDS, id, id);
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, new UserMapper());
    }

    @Override
    public List<User> getMutualFriends(Long userId, Long friendId) {
        return jdbcTemplate.query(MUTUAL_FRIEND_QUERY, new UserMapper(), userId, friendId);
    }

    @Override
    public List<User> getMutualSubscriptions(Long userId, Long friendId) {
        return jdbcTemplate.query(MUTUAL_SUBSCRIPTIONS_QUERY, new UserMapper(), userId, friendId);
    }

    @Override
    public void removeFriend(Long userId, Long friendId) {
        jdbcTemplate.update(DELETE_USER_FROM_FRIENDS, userId, friendId);
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        findUserById(userId);
        findUserById(friendId);
        if (isFriends(userId, friendId)) {
            throw new RuntimeException("Entity was created");
        }
        if (isFriends(friendId, userId)) {
            Object[] params = new Object[]{friendId, userId};
            jdbcTemplate.update(ACCEPT_FRIENDSHIP, params);
        } else {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("user_id", userId);
            parameters.put("friend_id", friendId);
            parameters.put("confirm", false);
            insertForFriends.execute(parameters);
        }
    }

    private boolean isFriends(Long userId, Long friendId) {
        return !jdbcTemplate.query(GET_FRIENDSHIP, new UserMapper(), userId, friendId).isEmpty();
    }
}
