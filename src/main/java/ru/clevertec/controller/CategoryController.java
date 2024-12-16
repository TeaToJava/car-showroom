package ru.clevertec.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.CategoryNameUpdateDto;
import ru.clevertec.service.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        categoryService.save(categoryDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategory(@PathVariable("id") @Valid @NotBlank Long id) {
        return categoryService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategoryByCar(@PathVariable("id") @Valid @NotBlank Long id,
                                    @RequestBody @Valid CategoryNameUpdateDto categoryPatchDto) {
        categoryService.update(id, categoryPatchDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") @Valid @NotBlank Long id) {
        categoryService.deleteById(id);
    }
}
