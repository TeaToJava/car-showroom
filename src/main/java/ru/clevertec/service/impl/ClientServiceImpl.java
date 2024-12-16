package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.dto.ClientPatchDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.exception.ObjectNotFoundException;
import ru.clevertec.service.exception.ObjectType;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CarRepository carRepository;

    @Override
    public void buyCar(Long clientId, ClientPatchDto clientPatchDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CLIENT, clientId.toString()));
        Long carId = clientPatchDto.carId();
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR, carId.toString()));
        client.addCar(car);
        clientRepository.save(client);
    }

    @Override
    public List<ClientDto> getAll() {
        return clientMapper.map(clientRepository.findAll());
    }

    @Override
    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CLIENT, id.toString()));
        return clientMapper.toDto(client);
    }

    @Override
    public void save(ClientDto clientDto) {
        if (clientDto.getRegistrationDate() == null) {
            clientDto.setRegistrationDate(LocalDateTime.now());
        }
        clientRepository.save(clientMapper.toClient(clientDto));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}
