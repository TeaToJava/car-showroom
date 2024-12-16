package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.CarUpdatePriceDto;
import ru.clevertec.dto.CarWithShowroomDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Category;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.service.CarService;
import ru.clevertec.service.exception.ObjectType;
import ru.clevertec.service.exception.ObjectNotFoundException;
import ru.clevertec.repository.specs.CarSpecs;


import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarDto> getAll() {
        return carMapper.map(carRepository.findAll());
    }

    @Override
    public CarDto getById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR, id.toString()));
        return carMapper.toCarDto(car);
    }

    @Override
    public void save(CarDto carDto) {
        carRepository.save(carMapper.toCar(carDto));
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updatePrice(Long id, CarUpdatePriceDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR, id.toString()));
        car.setPrice(carDto.price());
        carRepository.save(car);
    }

    public List<CarDto> findCarsByFilters(String carMake, int year, Category category, double minPrice, double maxPrice) {
        return carMapper.map(carRepository.findAll(Specification.anyOf(List.of(
                CarSpecs.hasCarMake(carMake),
                CarSpecs.greaterThanPrice(minPrice),
                CarSpecs.lessThanPrice(maxPrice),
                CarSpecs.hasYear(year)))));
    }

    @Override
    public List<CarWithShowroomDto> getAllCarsWithShowrooms() {
        List<Car> cars = carRepository.findAllCarsWithShowroom();
        return carMapper.mapWithShowroom(cars);
    }

}
