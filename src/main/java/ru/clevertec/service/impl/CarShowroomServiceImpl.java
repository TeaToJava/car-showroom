package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.dto.CarShowroomPatchDto;
import ru.clevertec.dto.CarShowroomUpdateAddressDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.mapper.CarShowroomMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.service.CarShowroomService;
import ru.clevertec.service.exception.ObjectType;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CarShowroomServiceImpl implements CarShowroomService {

    private final CarShowroomRepository carShowroomRepository;
    private final CarRepository carRepository;
    private final CarShowroomMapper carShowroomMapper;
    private final CarMapper carMapper;

    public void assignCarToShowroom(Long id, CarShowroomPatchDto patchDto) {
        CarShowroom carShowroom = carShowroomRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR_SHOWROOM, id.toString()));
        Long carId = patchDto.carId();
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR, carId.toString()));
        carShowroom.addCar(car);
        carShowroomRepository.save(carShowroom);
    }

    @Override
    public void updateAddress(Long id, CarShowroomUpdateAddressDto carShowroomUpdateAddressDto) {
        CarShowroom carShowroom = carShowroomRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR_SHOWROOM, id.toString()));
        carShowroom.setAddress(carShowroomUpdateAddressDto.address());
        carShowroomRepository.save(carShowroom);
    }

    @Override
    public List<CarShowroomDto> getAll() {
        return carShowroomMapper.map(carShowroomRepository.findAll());
    }

    @Override
    public CarShowroomDto getById(Long id) {
        return carShowroomMapper.toDto(carShowroomRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR_SHOWROOM, id.toString())));
    }

    @Override
    public void save(CarShowroomDto carShowroomDto) {
        carShowroomRepository.save(carShowroomMapper.toCarShowroom(carShowroomDto));
    }

    @Override
    public void deleteById(Long id) {
        carShowroomRepository.deleteById(id);
    }
}
