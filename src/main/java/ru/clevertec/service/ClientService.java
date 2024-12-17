package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;

public interface ClientService extends CommonService<Client>{
    void buyCar(Client client, Car car);
}
