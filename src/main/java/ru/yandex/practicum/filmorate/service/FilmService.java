package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.FilmRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {
    private final FilmRepository repository;
    int idCounter = 1;
    public FilmService() {
        repository = new FilmRepository();
    }

    public Film addFilm(Film film) {
        populateId(film);
        repository.getFilms().put(film.getId(), film);
        log.info("Add film");
        return film;
    }

    public Film updateFilm(Film film) {
        Map<Integer, Film> filmsMap = repository.getFilms();
        if (filmsMap.containsKey(film.getId())){
            filmsMap.put(film.getId(),film);
            log.info("Update film");
            return film;
        } else {
            throw new IllegalArgumentException("Incorrect Id");
        }
    }

    public List<Film> getAllFilms() {
        log.info("Get all films");
        return new ArrayList<>(repository.getFilms().values());
    }

    private void populateId(Film film) {
        if (film.getId() == null) {
            film.setId(idCounter);
            idCounter++;
        }
    }
}
