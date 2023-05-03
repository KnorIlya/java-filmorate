package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.annotation.LogExecution;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping()
    @LogExecution()
    public ResponseEntity<UserDto> create(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(user));
    }

    @PutMapping()
    @LogExecution()
    public ResponseEntity<UserDto> update(@Valid @RequestBody User user) {
        return ResponseEntity.ok(service.update(user));
    }

    @PutMapping("/{id}/friends/{friendId}")
    @LogExecution(withArgs = true)
    public ResponseEntity<Void> addFriend(@PathVariable Long id,
                                          @PathVariable Long friendId) {
        service.addFriend(id, friendId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping({"{id}"})
    @LogExecution()
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("/{id}/friends")
    @LogExecution()
    public ResponseEntity<List<UserDto>> getSubscriptions(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllSubscriptions(id));
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    @LogExecution()
    public ResponseEntity<List<UserDto>> getMutualSubscriptions(@PathVariable Long id,
                                                                @PathVariable @Positive Long otherId) {
        return ResponseEntity.ok(service.getMutualSubscriptions(id, otherId));
    }

    @GetMapping()
    @LogExecution()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    @LogExecution(withArgs = true)
    public ResponseEntity<Void> removeFriend(@PathVariable Long id,
                                             @PathVariable Long friendId) {
        service.removeFriend(id, friendId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @LogExecution(withArgs = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
