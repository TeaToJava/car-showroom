package ru.clevertec.dto;

import jakarta.validation.constraints.NotNull;

public record ClientPatchDto(@NotNull Long carId) {
}
