package ru.clevertec.service;

import java.util.List;

public interface CommonService<T> {
    List<T> getAll();

    T getById(Long id);

    void deleteById(Long id);

    void save(T entity);

}
