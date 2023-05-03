package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id;
    @Email(message = "Incorrect Email")
    @NotNull(message = "Email can't be empty")
    String email;
    @EqualsAndHashCode.Exclude
    String name;
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Birthday can't be empty")
    @Past(message = "Birthday must be correct")
    LocalDate birthday;
    @EqualsAndHashCode.Exclude
    @NotBlank(message = "Login can't be empty")
    @Pattern(regexp = "\\S+", message = "Login should be without space")
    String login;
}
