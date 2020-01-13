package com.lms.repository;

import com.lms.models.TacheEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TacheEmployeeRepository extends JpaRepository<TacheEmployee, Integer> {

    @Query(value = "SELECT * FROM tache_employee WHERE employee_tache_id = ?1", nativeQuery = true)
    List<TacheEmployee> EmployeeTache(String employeeId);

    @Query(value = "SELECT * FROM tache_employee WHERE employee_tache_id = ?1 AND done = 1", nativeQuery = true)
    List<TacheEmployee> EmployeeTacheDone(String employeeId);

    @Query(value = "SELECT * FROM tache_employee WHERE tache_employee_id = ?1", nativeQuery = true)
    List<TacheEmployee> TacheEmployee(String tacheId);


}
