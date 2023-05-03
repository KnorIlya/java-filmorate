package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FilmMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Film.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .releaseDate(LocalDate.parse(rs.getString("release_date")))
                .duration(rs.getInt("duration"))
                .mpa(Mpa.builder()
                        .id(rs.getLong("mpa_id"))
                        .name(rs.getString("mpa_name"))
                        .build())
                .build();
    }
}
