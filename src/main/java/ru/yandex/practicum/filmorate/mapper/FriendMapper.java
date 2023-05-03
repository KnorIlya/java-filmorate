package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Friend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendMapper implements RowMapper<Friend> {
    @Override
    public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Friend.builder()
                .userID(rs.getLong("user_id"))
                .friendId(rs.getLong("friend_id"))
                .confirm(rs.getBoolean("confirm"))
                .build();
    }
}
