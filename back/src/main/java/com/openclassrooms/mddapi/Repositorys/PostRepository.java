package com.openclassrooms.mddapi.Repositorys;

import com.openclassrooms.mddapi.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de dépot pour la gestion des articles POST dans la base de données .
 *
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.topic_id IN (SELECT t FROM User u JOIN u.topics t WHERE u.id = :userId)")
    List<Post> findAllByTopic_UserId(@Param("userId") Integer userId);
}
