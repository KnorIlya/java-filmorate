package ru.yandex.practicum.filmorate.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Film> add(@Valid @RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addFilm(film));
    }

    @PutMapping()
    public ResponseEntity<Film> update(@Valid @RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.updateFilm(film));
    }

    @GetMapping()
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllFilms());
    }
}
