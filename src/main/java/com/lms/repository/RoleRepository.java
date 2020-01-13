package com.lms.repository;

import com.lms.models.Role;
import com.lms.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findById(int id);
    @Query(nativeQuery = true, value = "select * from role")
    public List<Role> getAllActiveRoles();
}
