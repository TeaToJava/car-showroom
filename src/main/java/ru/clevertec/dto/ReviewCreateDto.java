package ru.clevertec.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCreateDto {

    private String text;
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    @NotNull
    @Positive
    private Long clientId;
    @NotNull
    @Positive
    private Long carId;
}