package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class UserMapper implements RowMapper<User> {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .birthday(user.getBirthday())
                .login(user.getLogin())
                .build();
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .login(rs.getString("login"))
                .birthday(LocalDate.parse(Objects.requireNonNull(rs.getString("birthday"))))
                .email(rs.getString("email"))
                .build();
    }
}
