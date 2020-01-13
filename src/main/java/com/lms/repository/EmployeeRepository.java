package com.lms.repository;

import com.lms.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findById(int id);

    Employee findByEmailEqualsAndPasswordEquals(String email,String pass);
}
