package org.example.serviceforemployees.repository;

import org.example.serviceforemployees.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
