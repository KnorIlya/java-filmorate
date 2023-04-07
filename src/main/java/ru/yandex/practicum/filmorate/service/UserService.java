package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.UserDto;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage storage;

    public User getUser(Long id) {
        User user = storage.getById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    public User addFriend(Long id, Long friendId) {
        Map<Long, User> userMap = storage.getStorage();
        if (userMap.containsKey(id) && userMap.containsKey(friendId)) {
            User friend = storage.getById(friendId);
            User user = storage.getById(id);
            user.getFriend().add(userToFriend(friend));
            friend.getFriend().add(userToFriend(user));
            return user;
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public User removeFriend(Long id, Long friendId) {
        Map<Long, User> userMap = storage.getStorage();
        if (userMap.containsKey(id) && userMap.containsKey(friendId)) {
            User user = storage.getById(id);
            User friend = storage.getById(friendId);
            user.getFriend().remove(userToFriend(friend));
            friend.getFriend().remove(userToFriend(user));
            return user;
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public Set<UserDto> getAllFriends(Long id) {
        User user = storage.getById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user.getFriend();
    }

    public Set<UserDto> getMutualFriends(Long id, Long otherId) {
        User user = storage.getById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        User otherUser = storage.getById(otherId);
        if (otherUser == null) {
            throw new NotFoundException("Other user not found");
        }
        Set<UserDto> intersectionUserDtos = new java.util.HashSet<>(Set.copyOf(user.getFriend()));
        intersectionUserDtos.retainAll(otherUser.getFriend());
        return intersectionUserDtos;
    }

    private UserDto userToFriend(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .birthday(user.getBirthday())
                .login(user.getLogin())
                .build();
    }
}
