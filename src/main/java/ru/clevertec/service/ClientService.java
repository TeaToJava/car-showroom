package ru.clevertec.service;

import ru.clevertec.dto.ClientDto;
import ru.clevertec.dto.ClientPatchDto;

public interface ClientService extends CommonService<ClientDto> {
    void buyCar(Long clientId, ClientPatchDto clientPatchDto);
}
