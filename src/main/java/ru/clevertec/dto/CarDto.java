package ru.clevertec.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private Long id;
    @NotNull
    private String carModel;
    @NotNull
    private String carMake;
    @NotNull
    private int yearOfProduction;
    @NotNull @Positive
    private double price;
}
