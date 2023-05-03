package ru.yandex.practicum.filmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.GenreDao;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

import static ru.yandex.practicum.filmorate.dao.SqlQueries.GET_ALL_GENRES;
import static ru.yandex.practicum.filmorate.dao.SqlQueries.GET_GENRE;

@Component
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Genre getById(Long id) {
        return jdbcTemplate.queryForObject(GET_GENRE, new GenreMapper(), id);
    }

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(GET_ALL_GENRES, new GenreMapper());
    }
}
