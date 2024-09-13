package com.openclassrooms.mddapi.Repositorys;

import com.openclassrooms.mddapi.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de dépot pour la gestion des articles POST dans la base de données .
 *
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
