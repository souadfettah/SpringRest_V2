package com.lms.repository;

import com.lms.models.Employee;
import com.lms.models.Projet;
import com.lms.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Serializable> {

    public Service findById(int id);
    @Query(nativeQuery = true, value = "select * from service")
    public List<Service> getAllActiveServices();
}
