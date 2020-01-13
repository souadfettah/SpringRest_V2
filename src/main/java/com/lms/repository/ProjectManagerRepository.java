package com.lms.repository;

import com.lms.models.Employee;
import com.lms.models.LeaveDetails;
import com.lms.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository(value = "projectManageRepository")
public interface ProjectManagerRepository extends JpaRepository<Projet, Serializable> {
    public Projet findById(int id);


    @Query(nativeQuery = true, value = "select array_to_json(array_agg(row_to_json(t))) from (select employee_name||' on leave' as title,to_char(from_date,'YYYY-MM-DD') as start,to_char(to_date,'YYYY-MM-DD') as end from leave_details) as t;")
    public Object getAllProjectsAsJsonArray();

    @Query(nativeQuery = true, value = "select * from projet")
    public List<Projet> getAllActiveProjects();

    @Query(nativeQuery = true, value = "select * from projet where username=? order by id desc")
    public List<LeaveDetails> getAllLeavesOfUser(String username);
}
