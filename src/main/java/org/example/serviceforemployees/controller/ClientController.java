package org.example.serviceforemployees.controller;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.model.Client;
import org.example.serviceforemployees.service.ApplicationService;
import org.example.serviceforemployees.service.ClientService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<Client>> getALL() {
        try {
            return ResponseEntity.ok(clientService.findAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id) {
        Optional<Client> ts = clientService.findById(id);

        if (ts.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<List<Application>> getApplicationsByClientId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(applicationService.findApplicationsByClientId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Пример Json запроса
     * {
     *     "name": "newClient",
     *     "number": 6
     * }
     */
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
