CREATE SCHEMA IF NOT EXISTS filmorate;

CREATE TABLE IF NOT EXISTS filmorate.users (
                         id bigint generated always as identity PRIMARY KEY,
                         login varchar unique not null ,
                         email varchar unique not null ,
                         name varchar,
                         birthday date not null
);

CREATE TABLE IF NOT EXISTS filmorate.films (
                         id bigint generated always as identity PRIMARY KEY,
                         name varchar,
                         release_date date,
                         duration smallint,
                         description varchar,
                         rating_id bigint
);

CREATE TABLE IF NOT EXISTS filmorate.genres (
                          id bigint generated always as identity PRIMARY KEY,
                          name varchar unique not null
);

CREATE TABLE IF NOT EXISTS filmorate.ratings (
                           id bigint generated always as identity PRIMARY KEY,
                           name varchar unique not null
);

CREATE TABLE IF NOT EXISTS filmorate.friends (
                           user_id bigint,
                           friend_id bigint,
                           confirm boolean,
                           PRIMARY KEY (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS filmorate.film_genres (
                               film_id bigint,
                               genre_id bigint,
                               PRIMARY KEY (film_id, genre_id)
);

CREATE TABLE IF NOT EXISTS filmorate.like_info (
                             user_id bigint,
                             film_id bigint,
                             PRIMARY KEY (user_id, film_id)
);

ALTER TABLE filmorate.friends ADD FOREIGN KEY (user_id) REFERENCES filmorate.users (id);

ALTER TABLE filmorate.friends ADD FOREIGN KEY (friend_id) REFERENCES filmorate.users (id);

ALTER TABLE filmorate.film_genres ADD FOREIGN KEY (film_id) REFERENCES filmorate.films (id);

ALTER TABLE filmorate.film_genres ADD FOREIGN KEY (genre_id) REFERENCES filmorate.genres (id);

ALTER TABLE filmorate.films ADD FOREIGN KEY (rating_id) REFERENCES filmorate.ratings (id);

ALTER TABLE filmorate.like_info ADD FOREIGN KEY (user_id) REFERENCES filmorate.users (id);

ALTER TABLE filmorate.like_info ADD FOREIGN KEY (film_id) REFERENCES filmorate.films (id);