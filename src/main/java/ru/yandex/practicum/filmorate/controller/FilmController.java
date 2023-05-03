package ru.yandex.practicum.filmorate.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.annotation.LogExecution;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService service;


    @PostMapping()
    @LogExecution()
    public ResponseEntity<Film> add(@Valid @RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(film));
    }

    @PutMapping()
    @LogExecution()
    public ResponseEntity<Film> update(@Valid @RequestBody Film film) {
        return ResponseEntity.ok(service.update(film));
    }

    @PutMapping("/{id}/like/{userId}")
    @LogExecution(withArgs = true)
    public ResponseEntity<Void> addLike(@PathVariable Long id, @PathVariable Long userId) {
        service.addLike(id, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @LogExecution()
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    @LogExecution()
    public ResponseEntity<Film> getFilm(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/popular")
    @LogExecution()
    public ResponseEntity<List<Film>> getPopularFilms(@RequestParam(defaultValue = "10") Integer count) {
        return ResponseEntity.ok(service.getPopularFilms(count));
    }

    @DeleteMapping("/{id}/like/{userId}")
    @LogExecution(withArgs = true)
    public ResponseEntity<Void> removeLike(@PathVariable Long id, @PathVariable Long userId) {
        service.removeLike(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @LogExecution(withArgs = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
