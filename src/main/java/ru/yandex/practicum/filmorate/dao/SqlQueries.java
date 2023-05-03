package ru.yandex.practicum.filmorate.dao;

public class SqlQueries {

    public static final String MUTUAL_FRIEND_QUERY = "select * from filmorate.users as u where u.id in " +
            "(select f1.friend_id from filmorate.friends f1 join filmorate.friends f2 on f1.friend_id=f2.friend_id " +
            "where f1.user_id = ? and f2.user_id= ? and f1.confirm = true and f2.confirm = true)";

    public static final String MUTUAL_SUBSCRIPTIONS_QUERY = "select * from filmorate.users as u where u.id in " +
            "(select f1.friend_id from filmorate.friends f1 join filmorate.friends f2 on f1.friend_id=f2.friend_id " +
            "where f1.user_id = ? and f2.user_id= ?)";
    public static final String FIND_USER_BY_ID = "select * from filmorate.users where id = ?";
    public static final String GET_ALL_USER_FRIENDS = "select * from filmorate.users where id in (select friend_id from filmorate.friends where (user_id = ? or friend_id = ?) and confirm = true)";
    public static final String GET_ALL_USER_SUBSCRIPTIONS = "select * from filmorate.users where id in (select friend_id from filmorate.friends where user_id = ?)";
    public static final String UPDATE_USER = "update filmorate.users set name = ?, email = ?, login = ?, birthday = ? where id = ?";
    public static final String ACCEPT_FRIENDSHIP = "update filmorate.friends set confirm = true where user_id = ? and friend_id = ?";
    public static final String DELETE_USER_FROM_FRIENDS = "delete from filmorate.friends where user_id = ? and friend_id = ?";
    public static final String DELETE_USER_FROM_TABLE_FRIENDS = "delete from filmorate.friends where user_id = ? or friend_id = ?";
    public static final String DELETE_USER = "delete from filmorate.users where id = ?";
    public static final String GET_ALL_USERS = "select * from filmorate.users";
    public static final String GET_FRIENDSHIP = "select * from filmorate.friends where user_id = ? and friend_id = ?";
    public static final String GET_ALL_MPA = "select * from filmorate.ratings order by id";
    public static final String GET_MPA = "select * from filmorate.ratings where id = ?";
    public static final String GET_GENRE = "select * from filmorate.genres where id = ?";
    public static final String GET_ALL_GENRES = "select * from filmorate.genres order by id";
    public static final String GET_ALL_FILM_GENRES = "select * from filmorate.genres as g where g.id in " +
            "(select genre_id from filmorate.film_genres as fg join filmorate.films as f on fg.film_id = f.id where f.id = ?)";
    public static final String GET_ALL_FILMS = "select f.id as id, f.name as name, f.description as description, f.release_date as release_date, f.duration as duration, " +
            "r.id as mpa_id, r.name as mpa_name from filmorate.films as f " +
            "join filmorate.ratings as r on f.rating_id = r.id";
    public static final String UPDATE_FILM = "update filmorate.films set name = ?, release_date = ?, description = ?, duration = ?, rating_id = ? where id = ?";
    public static final String GET_FILM_BY_ID = "select f.id as id, f.name as name, f.description as description, f.release_date as release_date, f.duration as duration, " +
            "r.id as mpa_id, r.name as mpa_name from filmorate.films as f " +
            "join filmorate.ratings as r on f.rating_id = r.id " +
            "where f.id = ?";
    public static final String GET_POPULAR_FILMS = "select f.id as id, f.name as name, f.description as description, f.release_date as release_date, " +
            "f.duration as duration, r.id as mpa_id, r.name as mpa_name from filmorate.films as f " +
            "join filmorate.ratings as r on f.rating_id = r.id " +
            "left join filmorate.like_info as li on li.film_id = f.id " +
            "group by f.id, r.id order by count(li.user_id) desc " +
            "FETCH FIRST ? ROWS ONLY";

    public static final String REMOVE_LIKE = "delete from filmorate.like_info where film_id = ? and user_id = ?";
    public static final String REMOVE_ALL_FILMS_LIKE = "delete from filmorate.like_info where film_id = ?";
    public static final String REMOVE_FILM = "delete from filmorate.films where film_id = ?";
    public static final String REMOVE_FILMS_GENRE = "delete from filmorate.film_genres where film_id = ?";

}
