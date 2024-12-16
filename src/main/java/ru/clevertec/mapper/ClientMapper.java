package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.entity.Client;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);

    Client toClient(ClientDto clientDto);

    List<ClientDto> map(List<Client> clients);
}
