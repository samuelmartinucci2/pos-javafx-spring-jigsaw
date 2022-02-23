package com.samuel.repository;

import com.samuel.repository.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByCpf(String cpf);

}
