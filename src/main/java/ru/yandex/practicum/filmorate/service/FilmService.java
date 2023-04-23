package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.FilmDao;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmDao filmDao;
    private final UserService userService;

    public void addLike(Long filmId, Long userId) {
        filmDao.addLikeById(filmId, userId);
    }

    public void removeLike(Long filmId, Long userId) {
        userService.getUser(userId);
        filmDao.removeLike(filmId, userId);
    }

    public void delete(Long id) {
        filmDao.delete(id);
    }

    public Film create(Film film) {
        return filmDao.create(film);
    }

    public Film update(Film film) {
        return filmDao.update(film);
    }

    public List<Film> getAll() {
        return filmDao.getAll();
    }

    public Film getById(Long id) {
        return filmDao.getById(id);
    }

    public List<Film> getPopularFilms(Integer count) {
        return filmDao.getPopular(count);
    }
}
