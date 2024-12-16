package ru.clevertec.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.dto.ClientPatchDto;
import ru.clevertec.service.ClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients() {
        return clientService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody @Valid ClientDto clientDto) {
        clientService.save(clientDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClient(@PathVariable("id") @Valid @NotBlank Long id) {
        return clientService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateClientByCar(@PathVariable("id") @Valid @NotBlank Long id,
                                  @RequestBody @Valid ClientPatchDto clientPatchDto) {
        clientService.buyCar(id, clientPatchDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") @Valid @NotBlank Long id) {
        clientService.deleteById(id);
    }
}
