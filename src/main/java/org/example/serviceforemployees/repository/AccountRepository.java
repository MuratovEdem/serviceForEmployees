package org.example.serviceforemployees.repository;

import org.example.serviceforemployees.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLogin(String login);
}
