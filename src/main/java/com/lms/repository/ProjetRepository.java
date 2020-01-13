package com.lms.repository;

import com.lms.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    Projet findById(int id);
}
