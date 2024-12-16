package ru.clevertec.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryNameUpdateDto(@NotBlank String categoryName) {
}
