package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.GenreDao;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreDao genreDao;

    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
