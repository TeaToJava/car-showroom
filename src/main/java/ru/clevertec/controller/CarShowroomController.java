package ru.clevertec.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.dto.CarShowroomPatchDto;
import ru.clevertec.dto.CarShowroomUpdateAddressDto;
import ru.clevertec.service.CarShowroomService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/carshowrooms")
public class CarShowroomController {
    private CarShowroomService carShowroomService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CarShowroomDto> getAllCarShowrooms() {
        return carShowroomService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createCarShowroom(@RequestBody @Valid CarShowroomDto carShowroomDto) {
        carShowroomService.save(carShowroomDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarShowroomDto getCarShowroom(@PathVariable("id") @Valid @NotBlank Long id) {
        return carShowroomService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCarShowroom(@PathVariable("id") @Valid @NotBlank Long id,
                                  @RequestBody @Valid CarShowroomUpdateAddressDto carShowroomUpdateDto) {
        carShowroomService.updateAddress(id, carShowroomUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarShowroom(@PathVariable("id") @Valid @NotBlank Long id) {
        carShowroomService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignCarToShowroom(@PathVariable("id") @Valid @NotBlank Long id,
                                    @RequestBody @Valid CarShowroomPatchDto carShowroomPatchDto) {
        carShowroomService.assignCarToShowroom(id, carShowroomPatchDto);
    }

}
