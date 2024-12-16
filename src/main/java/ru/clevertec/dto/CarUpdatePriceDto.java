package ru.clevertec.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CarUpdatePriceDto(@NotNull @Positive double price) {
}
