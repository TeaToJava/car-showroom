package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Category;

import java.util.List;

public interface CarService extends CommonService<Car> {
    List<Car> pagination(int first, int numberPerPage);
    List<Car> findCarsByFilters(String carMake, String year, Category category, String minPrice, String maxPrice);
    List<Car> listCarsByPrice(String order);
    List<Car> getAllCarsWithShowrooms();
    List<Car> getCarsWithShowrooms();
}
