package ru.yandex.practicum.filmorate.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmDto {

    Long id;
    String name;
    String description;
    LocalDate releaseDate;
    Integer duration;
    Genre genre;
    Mpa mpa;
}
