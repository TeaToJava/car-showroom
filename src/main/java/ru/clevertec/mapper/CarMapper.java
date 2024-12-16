package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.CarWithShowroomDto;
import ru.clevertec.entity.Car;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto toCarDto(Car car);

    Car toCar(CarDto carDto);

    List<CarDto> map(List<Car> cars);

    List<CarWithShowroomDto> mapWithShowroom(List<Car> cars);
}
