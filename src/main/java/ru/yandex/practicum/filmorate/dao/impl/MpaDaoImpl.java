package ru.yandex.practicum.filmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.MpaDao;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

import static ru.yandex.practicum.filmorate.dao.SqlQueries.GET_ALL_MPA;
import static ru.yandex.practicum.filmorate.dao.SqlQueries.GET_MPA;

@Component
@RequiredArgsConstructor
public class MpaDaoImpl implements MpaDao {
    public final JdbcTemplate jdbcTemplate;

    @Override
    public Mpa getById(Long id) {
        return jdbcTemplate.queryForObject(GET_MPA, new MpaMapper(), id);
    }

    @Override
    public List<Mpa> getAll() {
        return jdbcTemplate.query(GET_ALL_MPA, new MpaMapper());
    }
}
