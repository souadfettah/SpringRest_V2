package com.lms.service;

import com.lms.models.Commentaire;
import com.lms.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    public Commentaire saveCommentaire(Commentaire commentaire)
    {
        return  commentaireRepository.save(commentaire);
    }

    public List<Commentaire> getTaskCommentsByUser(int task_id, int user_id)
    {
        return commentaireRepository.commentaireTache(task_id,user_id);
    }

    public List<Commentaire> getTaskComments(int task_id)
    {
        return commentaireRepository.getCommentByTaskId(task_id);
    }
}
