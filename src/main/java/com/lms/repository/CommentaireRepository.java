package com.lms.repository;

import com.lms.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

    Commentaire findById(int id);

    @Query(value = "SELECT * FROM commentaire WHERE tache_id = ?1 AND employee_id = ?2", nativeQuery = true)
    List<Commentaire> commentaireTache(int taskId, int user_id);

    @Query(value = "SELECT * FROM commentaire WHERE tache_id = ?1", nativeQuery = true)
    List<Commentaire> getCommentByTaskId(int taskId);
}
