package org.example.serviceforemployees.repository;

import org.example.serviceforemployees.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
