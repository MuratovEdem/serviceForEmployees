package org.example.serviceforemployees.repository;

import org.example.serviceforemployees.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findApplicationsByClientId(Long id);

    @Query(nativeQuery = true, value = "select a.* from applications a join employees_applications c on a.id = c.application_id where c.employee_id = :employeeId")
    List<Application> findByEmployeeId(Long employeeId);

    @Query(nativeQuery = true, value = "select a.* from applications a " +
            "left join employees_applications c on a.id = c.application_id " +
            "left join employees emp on emp.id = c.employee_id " +
            "left join accounts acc on acc.id = emp.account_id " +
            "where a.is_completed = :isCompleted and acc.login = :login")
    List<Application> findByEmployeeLoginWhereIsCompleted(String login, Boolean isCompleted);

    @Query(nativeQuery = true, value = "select a.* from applications a " +
            "left join employees_applications c on a.id = c.application_id " +
            "left join employees emp on emp.id = c.employee_id " +
            "left join accounts acc on cl.id = emp.account_id " +
            "where acc.login = :login")
    List<Application> findByEmployeeLogin(String login);

}
