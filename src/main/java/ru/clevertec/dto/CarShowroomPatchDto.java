package ru.clevertec.dto;

import jakarta.validation.constraints.NotNull;

public record CarShowroomPatchDto(@NotNull Long carId) {
}
