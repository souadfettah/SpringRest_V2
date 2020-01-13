package com.lms.repository;

import com.lms.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository(value = "userInfoRepository")
public interface UserInfoRepository extends JpaRepository<Employee, Serializable> {

    public Employee findByEmail(String email);

    public List<Employee> findAllByOrderById();

    public Employee findById(int id);

    @Transactional
    @Modifying
    @Query(value = "update employee set active=false where id=?", nativeQuery = true)
    public void blockUser(int id);

    @Transactional
    @Modifying
    @Query(value = "update employee set active=true where id=?", nativeQuery = true)
    public void unBlockUser(int id);
}
