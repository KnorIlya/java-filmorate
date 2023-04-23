package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.annotation.LogExecution;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService service;

    @LogExecution()
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @LogExecution()
    @GetMapping
    public ResponseEntity<List<Genre>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
