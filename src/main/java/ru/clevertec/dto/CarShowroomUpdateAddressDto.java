package ru.clevertec.dto;

import jakarta.validation.constraints.NotBlank;

public record CarShowroomUpdateAddressDto(@NotBlank String address) {
}
