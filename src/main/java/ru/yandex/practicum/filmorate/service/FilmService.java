package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.comparator.FilmComparator;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage storage;

    public Film addLike(Long id, Long userId) {
        Film film = storage.getById(id);
        if (film == null) {
            throw new NotFoundException("Film not found");
        }
        film.getLikes().add(userId);
        return film;
    }

    public Film removeLike(Long id, Long userId) {
        Map<Long, Film> films = storage.getStorage();
        if (!films.containsKey(userId)) {
            throw new NotFoundException("Film not found");
        }
        Film film = storage.getById(id);
        film.getLikes().remove(userId);
        return film;
    }

    public List<Film> getPopularFilms(Integer count) {
        Comparator<Film> reverseFilmComparator = new FilmComparator().reversed();
        return storage.getAll().stream()
                .sorted(reverseFilmComparator)
                .limit(count)
                .collect(Collectors.toList());
    }

}
