package ru.clevertec.repository.specs;

import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Car_;

public class CarSpecs {
    public static Specification<Car> hasCarMake(String carMake) {
        return (car, cq, cb) -> cb.equal(car.get(Car_.CAR_MAKE), carMake);
    }

    public static Specification<Car> hasYear(int year) {
        return (car, cq, cb) -> cb.equal(car.get(Car_.YEAR_OF_PRODUCTION), year);
    }

    public static Specification<Car> greaterThanPrice(double price) {
        return (car, cq, cb) -> cb.greaterThan(car.get(Car_.PRICE), price);
    }

    public static Specification<Car> lessThanPrice(double price) {
        return (car, cq, cb) -> cb.lessThan(car.get(Car_.PRICE), price);
    }
}
