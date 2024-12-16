package ru.clevertec.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.CarUpdatePriceDto;
import ru.clevertec.dto.CarWithShowroomDto;
import ru.clevertec.service.CarService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/cars")
public class CarController {
    private CarService carService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto getCar(@PathVariable("id") @Valid @NotBlank Long id) {
        return carService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CarWithShowroomDto> getAllCarsWithShowrooms() {
        return carService.getAllCarsWithShowrooms();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody @Valid CarDto carDto) {
        carService.save(carDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCar(@PathVariable("id") @Valid @NotBlank Long id,
                          @RequestBody @Valid CarUpdatePriceDto carUpdatePriceDto) {
        carService.updatePrice(id, carUpdatePriceDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") @Valid @NotBlank Long id) {
        carService.deleteById(id);
    }
}
