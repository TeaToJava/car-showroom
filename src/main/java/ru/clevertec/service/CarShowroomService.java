package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;

public interface CarShowroomService extends CommonService<CarShowroom>{
    void assignCarToShowroom(Car car, CarShowroom carShowroom);
}
