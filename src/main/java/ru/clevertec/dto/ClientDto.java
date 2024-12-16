package ru.clevertec.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
    private Long id;
    @NotNull
    private String name;
    private List<String> contacts = new ArrayList<>();
    private LocalDateTime registrationDate;
}
