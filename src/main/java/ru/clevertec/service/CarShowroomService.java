package ru.clevertec.service;

import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.dto.CarShowroomPatchDto;
import ru.clevertec.dto.CarShowroomUpdateAddressDto;

public interface CarShowroomService extends CommonService<CarShowroomDto> {
    void assignCarToShowroom(Long id, CarShowroomPatchDto patchDto);

    void updateAddress(Long id, CarShowroomUpdateAddressDto carShowroom);
}
