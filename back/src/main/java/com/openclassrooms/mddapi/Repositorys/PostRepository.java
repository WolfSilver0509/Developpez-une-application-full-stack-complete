package com.openclassrooms.mddapi.Repositorys;

import com.openclassrooms.mddapi.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de dépot pour la gestion des articles POST dans la base de données .
 *
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
