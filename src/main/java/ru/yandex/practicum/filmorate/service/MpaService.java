package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.impl.MpaDaoImpl;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MpaService {

    private final MpaDaoImpl mpaDao;

    public List<Mpa> getAll() {
        return mpaDao.getAll();
    }

    public Mpa getById(Long id) {
        return mpaDao.getById(id);
    }
}
