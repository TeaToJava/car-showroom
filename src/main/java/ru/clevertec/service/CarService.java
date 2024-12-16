package ru.clevertec.service;

import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.CarUpdatePriceDto;
import ru.clevertec.dto.CarWithShowroomDto;
import ru.clevertec.entity.Category;

import java.util.List;

public interface CarService extends CommonService<CarDto> {

    List<CarDto> findCarsByFilters(String carMake, int year, Category category, double minPrice, double maxPrice);

    List<CarWithShowroomDto> getAllCarsWithShowrooms();

    void updatePrice(Long id, CarUpdatePriceDto carDto);
}
