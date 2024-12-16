package ru.clevertec.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.controller.error.ErrorMessage;
import ru.clevertec.service.exception.ObjectNotFoundException;

import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleException(@RequestBody Exception e) {
        return ErrorMessage.builder().message(e.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorMessage handleValidationExceptions(@RequestBody Exception e) {
        return ErrorMessage
                .builder()
                .message("Validation exception")
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ErrorMessage handleNotFoundException(@RequestBody Exception e) {
        return ErrorMessage
                .builder()
                .message(e.getMessage())
                .build();
    }

}
