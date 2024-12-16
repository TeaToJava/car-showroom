package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.CategoryNameUpdateDto;
import ru.clevertec.entity.Category;
import ru.clevertec.mapper.CategoryMapper;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.service.CategoryService;
import ru.clevertec.service.exception.ObjectNotFoundException;
import ru.clevertec.service.exception.ObjectType;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.map(categoryRepository.findAll());
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CATEGORY, id.toString()));
        return categoryMapper.toDto(category);
    }

    @Override
    public void save(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toCategory(categoryDto));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


    @Override
    public void update(Long id, CategoryNameUpdateDto categoryPatchDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CATEGORY, id.toString()));
        category.setName(categoryPatchDto.categoryName());
        categoryRepository.save(category);
    }
}
