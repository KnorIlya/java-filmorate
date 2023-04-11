package ru.yandex.practicum.filmorate.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final InMemoryFilmStorage storage;
    private final FilmService service;

    @PostMapping()
    public ResponseEntity<Film> add(@Valid @RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(storage.add(film));
    }

    @PutMapping()
    public ResponseEntity<Film> update(@Valid @RequestBody Film film) {
        return ResponseEntity.ok(storage.update(film));
    }

    @PutMapping("/{id}/like/{userId}")
    public ResponseEntity<Film> addLike(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(service.addLike(id, userId));
    }

    @GetMapping()
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(storage.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Film> getFilm(@PathVariable Long id) {
        return ResponseEntity.ok(storage.getById(id));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Film>> getPopularFilms(@RequestParam(defaultValue = "10") Integer count) {
        return ResponseEntity.ok(service.getPopularFilms(count));
    }

    @DeleteMapping("/{id}/like/{userId}")
    public ResponseEntity<Film> removeLike(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(service.removeLike(id, userId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storage.delete(id);
        return ResponseEntity.noContent().build();
    }
}
