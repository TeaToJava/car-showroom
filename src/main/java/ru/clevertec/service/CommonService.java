package ru.clevertec.service;

public interface CommonService<T> {
    T getById(Long id);

    void deleteById(Long id);

    void save(T entity);

    void update(Long id, T entity);
}
