package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    long idCounter = 1;
    private final Map<Long, Film> storage = new HashMap<>();

    public Map<Long, Film> getStorage() {
        return storage;
    }

    public Film add(Film film) {
        populateId(film);
        if (film.getLikes() == null) {
            film.setLikes(new HashSet<>());
        }
        storage.put(film.getId(), film);
        log.info("Add film");
        return film;
    }

    public Film update(Film film) {
        Map<Long, Film> filmsMap = storage;
        if (filmsMap.containsKey(film.getId())) {
            if (film.getLikes() == null) {
                film.setLikes(filmsMap.get(film.getId()).getLikes());
            }
            filmsMap.put(film.getId(), film);
            log.info("Update film");
            return film;
        } else {
            throw new NotFoundException("Film not found");
        }
    }

    public List<Film> getAll() {
        log.info("Get all films");
        return new ArrayList<>(storage.values());
    }

    public Film getById(Long id) {
        if (!storage.containsKey(id)) {
            throw new NotFoundException("Film not found");
        }
        return storage.get(id);
    }

    public void delete(Long id) {
        storage.remove(id);
    }

    private void populateId(Film film) {
        if (film.getId() == null) {
            film.setId(idCounter);
            idCounter++;
        }
    }
}
