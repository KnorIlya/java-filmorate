package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmDao {
    Film create(Film film);

    Film update(Film film);

    Film getById(Long id);

    List<Film> getAll();

    List<Film> getPopular(Integer limit);

    void addLikeById(Long filmId, Long userId);

    void removeLike(Long filmId, Long userId);

    void delete(Long id);
}
