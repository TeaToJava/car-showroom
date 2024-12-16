package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.entity.CarShowroom;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarShowroomMapper {
    CarShowroomDto toDto(CarShowroom carShowroom);

    CarShowroom toCarShowroom(CarShowroomDto carShowroomDto);

    List<CarShowroomDto> map(List<CarShowroom> carShowrooms);
}
