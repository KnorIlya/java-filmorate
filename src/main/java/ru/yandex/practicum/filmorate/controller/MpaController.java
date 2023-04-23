package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.annotation.LogExecution;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;

import java.util.List;

@RestController
@RequestMapping(value = "/mpa")
@RequiredArgsConstructor
public class MpaController {
    private final MpaService service;

    @LogExecution()
    @GetMapping("/{id}")
    public ResponseEntity<Mpa> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @LogExecution()
    @GetMapping
    public ResponseEntity<List<Mpa>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
