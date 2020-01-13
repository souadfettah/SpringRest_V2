package com.lms.repository;

import com.lms.models.Service;
import com.lms.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Integer> {

    Tache findById(int id);

    @Query(value = "SELECT * FROM tache WHERE projet_id = ?1", nativeQuery = true)
    List<Tache> ProjetTache(String tacheId);

}
