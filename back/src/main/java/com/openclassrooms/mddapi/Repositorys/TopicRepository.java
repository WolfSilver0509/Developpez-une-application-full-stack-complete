package com.openclassrooms.mddapi.Repositorys;

import com.openclassrooms.mddapi.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de dépot pour la gestion des thémes dans la base de données .
 *
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>  {

}