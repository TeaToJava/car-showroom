package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    List<CategoryDto> map(List<Category> categories);
}
