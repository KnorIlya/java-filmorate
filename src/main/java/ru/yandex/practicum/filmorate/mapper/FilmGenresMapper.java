package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.FilmGenres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmGenresMapper implements RowMapper<FilmGenres> {
    @Override
    public FilmGenres mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FilmGenres.builder()
                .filmId(rs.getLong("film_id"))
                .genreId(rs.getLong("genre_id"))
                .build();
    }
}
