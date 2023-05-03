package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.UserDao;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public UserDto getUser(Long id) {
        User user = userDao.findUserById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return UserMapper.toDto(user);
    }

    public UserDto create(User user) {
        userDao.create(user);
        return UserMapper.toDto(user);
    }

    public UserDto update(User user) {
        return UserMapper.toDto(userDao.update(user));
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addFriend(Long id, Long friendId) {
        userDao.addFriend(id, friendId);
    }

    public void removeFriend(Long userId, Long friendId) {
        userDao.removeFriend(userId, friendId);
    }


    public List<UserDto> getAllFriends(Long id) {
        return userDao.getAllFriends(id).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getAllSubscriptions(Long id) {

        return userDao.getAllSubscriptions(id).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getMutualFriends(Long id, Long friendId) {
        return userDao.getMutualFriends(id, friendId).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getMutualSubscriptions(Long id, Long friendId) {
        return userDao.getMutualSubscriptions(id, friendId).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}