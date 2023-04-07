package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Map;

public interface FilmStorage {

    Film add(Film film);

    Film update(Film film);

    List<Film> getAll();

    Film getById(Long id);

    Map<Long, Film> getStorage();

    void delete(Long id);
}
