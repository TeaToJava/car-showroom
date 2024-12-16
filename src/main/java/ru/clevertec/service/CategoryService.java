package ru.clevertec.service;

import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.CategoryNameUpdateDto;

public interface CategoryService extends CommonService<CategoryDto> {
    void update(Long id, CategoryNameUpdateDto categoryPatchDto);
}
