package org.example.serviceforemployees.controller;

import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping()
    public ResponseEntity<List<Application>> getApplicationsCurrentLoggedUserByStatus(@RequestParam(required = false) Boolean isCompleted) {
        return ResponseEntity.ok(applicationService.findApplicationsCurrentLoggedUserByStatus(isCompleted));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> get(@PathVariable Long id) {
        Optional<Application> ts = applicationService.findById(id);
        return ts.map(application -> ResponseEntity.status(HttpStatus.OK).body(application)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Пример Json запроса
     * {
     *     "client": {"id": 2},
     *     "text": "some application details",
     *     "isCompleted": false,
     *     "deadline": "2024-08-25",
     *     "employees": [{"id": 3}, {"id": 4}]
     * }
     */
    @PostMapping
    public ResponseEntity<Application> create(@RequestBody Application application) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.create(application));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
