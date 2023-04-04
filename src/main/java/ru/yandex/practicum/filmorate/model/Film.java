package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.annotation.DateIsAfter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class Film {

    private Integer id;
    @NotBlank(message = "Name can't be empty")
    private String name;
    @EqualsAndHashCode.Exclude
    @NotBlank
    @Size(max = 100)
    private String description;
    @DateIsAfter(min = "1895-03-21", allowNullValue = "true")
    @NotNull
    @EqualsAndHashCode.Exclude
    private LocalDate releaseDate;
    @NotNull(message = "Duration can't be null")
    @EqualsAndHashCode.Exclude
    @Min(1)
    private Integer duration;
}
