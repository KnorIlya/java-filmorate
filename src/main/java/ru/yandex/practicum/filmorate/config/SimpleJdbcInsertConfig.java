package ru.yandex.practicum.filmorate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
@RequiredArgsConstructor
public class SimpleJdbcInsertConfig {

    @Bean
    public SimpleJdbcInsert insertForUsers(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate).withSchemaName("filmorate")
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Bean
    public SimpleJdbcInsert insertForFriends(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate).withSchemaName("filmorate")
                .withTableName("friends");
    }

    @Bean
    public SimpleJdbcInsert insertForFilms(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate).withSchemaName("filmorate")
                .withTableName("films")
                .usingGeneratedKeyColumns("id");
    }

    @Bean
    public SimpleJdbcInsert insertForFilmsGenres(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate).withSchemaName("filmorate")
                .withTableName("film_genres");
    }
}
