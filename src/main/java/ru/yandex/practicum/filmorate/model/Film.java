package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.annotation.DateIsAfter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film {

    Long id;
    @NotBlank(message = "Name can't be empty")
    String name;
    @EqualsAndHashCode.Exclude
    @NotBlank
    @Size(max = 100)
    String description;
    @DateIsAfter(min = "1895-03-21", allowNullValue = "true")
    @NotNull
    @EqualsAndHashCode.Exclude
    LocalDate releaseDate;
    @NotNull(message = "Duration can't be null")
    @EqualsAndHashCode.Exclude
    @Min(1)
    Integer duration;
    Set<Genre> genres;
    @NotNull(message = "Rating can't be null")
    Mpa mpa;
}
