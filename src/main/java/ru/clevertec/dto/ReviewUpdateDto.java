package ru.clevertec.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewUpdateDto(@NotBlank String text) {
}
