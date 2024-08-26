package org.example.serviceforemployees.page.pageService;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.model.Client;
import org.example.serviceforemployees.page.pageDTO.ApplicationPageDto;
import org.example.serviceforemployees.page.pageDTO.ClientPageDto;
import org.example.serviceforemployees.service.ClientService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientPageService {

    private final ClientService clientService;

    public List<ClientPageDto> findAll() {
        return clientService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    public Optional<ClientPageDto> findById(Long id) {
        return clientService.findById(id)
                .map(this::convert);
    }

    private ClientPageDto convert(Client client) {
        ClientPageDto clientPageDto = new ClientPageDto();

        clientPageDto.setId(client.getId());
        clientPageDto.setName(client.getName());

        return clientPageDto;
    }
}
