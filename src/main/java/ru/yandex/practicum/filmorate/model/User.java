package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
public class User {

    private Integer id;
    @Email(message = "Incorrect Email")
    @NotNull(message = "Email can't be empty")
    private String email;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Birthday can't be empty")
    @Past(message = "Birthday must be correct")
    private LocalDate birthday;
    @EqualsAndHashCode.Exclude
    @NotBlank(message = "Login can't be empty")
    private String login;
}
