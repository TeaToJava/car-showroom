package ru.clevertec.controller.error;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {
    private String message;
}
