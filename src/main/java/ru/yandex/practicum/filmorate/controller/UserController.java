package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.UserDto;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final InMemoryUserStorage storage;
    private final UserService service;

    @PostMapping()
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(storage.create(user));
    }

    @PutMapping()
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        return ResponseEntity.ok(storage.update(user));
    }

    @PutMapping("/{id}/friends/{friendId}")
    public ResponseEntity<User> addFriend(@PathVariable Long id, @PathVariable Long friendId) {
        return ResponseEntity.ok(service.addFriend(id, friendId));
    }

    @GetMapping({"{id}"})
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<Set<UserDto>> getFriends(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllFriends(id));
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public ResponseEntity<Set<UserDto>> getMutualFriends(@PathVariable Long id, @PathVariable Long otherId) {
        return ResponseEntity.ok(service.getMutualFriends(id, otherId));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(storage.getAll());
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public ResponseEntity<User> removeFriend(@PathVariable Long id, @PathVariable Long friendId) {
        return ResponseEntity.ok(service.removeFriend(id, friendId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storage.delete(id);
        return ResponseEntity.noContent().build();
    }
}
