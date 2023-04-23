package ru.yandex.practicum.filmorate.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.FilmDao;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.*;

import static ru.yandex.practicum.filmorate.dao.SqlQueries.*;

@Component
public class FilmDaoImpl implements FilmDao {
    private final JdbcTemplate jdbcTemplate;
    @Qualifier("insertForFilms")
    private final SimpleJdbcInsert insertForFilms;

    public FilmDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert insertForFilms) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertForFilms = insertForFilms;
    }

    @Override
    public Film create(Film film) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", film.getName());
        parameters.put("description", film.getDescription());
        parameters.put("release_date", film.getReleaseDate());
        parameters.put("duration", film.getDuration());
        parameters.put("rating_id", film.getMpa().getId());
        Number newId = insertForFilms.executeAndReturnKey(parameters);
        film.setMpa(jdbcTemplate.queryForObject(GET_MPA, new MpaMapper(), film.getMpa().getId()));
        film.setId((long) newId);
        setGenres(film);
        return film;
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = jdbcTemplate.query(GET_ALL_FILMS, new FilmMapper());
        for (Film film : films) {
            setGenres(film);
        }
        return films;
    }

    private void setGenres(Film film) {
        if (film.getGenres() != null) {
            Set<Genre> genres = film.getGenres();
            for (Genre genre : genres) {
                insertFilmGenre(film.getId(), genre.getId());
            }
        }
        film.setGenres(new HashSet<>(getFilmGenre(film.getId())));
    }

    private List<Genre> getFilmGenre(Long filmId) {
        return jdbcTemplate.query(GET_ALL_FILM_GENRES, new GenreMapper(), filmId);
    }

    private void insertFilmGenre(Long filmId, Long genreId) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withSchemaName("filmorate")
                .withTableName("film_genres");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("film_id", filmId);
        parameters.put("genre_id", genreId);
        jdbcInsert.execute(parameters);
    }

    @Override
    public Film update(Film film) {
        jdbcTemplate.update(REMOVE_FILMS_GENRE, film.getId());
        Object[] params = new Object[]{film.getName(), film.getReleaseDate(), film.getDescription(), film.getDuration(),
                film.getMpa().getId(), film.getId()};
        if (jdbcTemplate.update(UPDATE_FILM, params) == 0) {
            throw new NotFoundException("Entity not found");
        }
        film.setMpa(jdbcTemplate.queryForObject(GET_MPA, new MpaMapper(), film.getMpa().getId()));
        setGenres(film);
        return film;
    }

    @Override
    public Film getById(Long id) {
        Film film = jdbcTemplate.queryForObject(GET_FILM_BY_ID, new FilmMapper(), id);
        film.setMpa(jdbcTemplate.queryForObject(GET_MPA, new MpaMapper(), film.getMpa().getId()));
        setGenres(film);
        return film;
    }

    @Override
    public List<Film> getPopular(Integer limit) {
        List<Film> films = jdbcTemplate.query(GET_POPULAR_FILMS, new FilmMapper(), limit);
        for (Film film : films) {
            setGenres(film);
        }
        return films;
    }

    @Override
    public void addLikeById(Long filmId, Long userId) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withSchemaName("filmorate")
                .withTableName("like_info");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", userId);
        parameters.put("Film_id", filmId);
        jdbcInsert.execute(parameters);
    }

    @Override
    public void removeLike(Long filmId, Long userId) {
        jdbcTemplate.update(REMOVE_LIKE, filmId, userId);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(REMOVE_ALL_FILMS_LIKE, id);
        jdbcTemplate.update(REMOVE_FILM, id);
    }
}
