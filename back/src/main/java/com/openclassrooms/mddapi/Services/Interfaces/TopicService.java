package com.openclassrooms.mddapi.Services.Interfaces;

// Classe de service responsable de la gestion des thémes.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des thémes.

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoCreate;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoGetAll;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoReponseMessage;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Models.Topic;
import org.springframework.http.ResponseEntity;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface TopicService {

    /**
     * Méthode pour récupérer tous les thémes.
     * Retourne une liste de toutes les entités Topic.
     */
     List<Topic> findAllTopics();

    /**
     * Méthode pour récupérer un théme par son ID.
     * Prend en entrée l'ID du théme.
     * Retourne un Optional contenant l'entité Topic si elle est trouvée.
     */
     Optional<Topic> findById(Integer id);

    /**
     * Méthode pour enregistrer un théme.
     * Prend en entrée une entité Topic et la sauvegarde dans la base de données.
     * Retourne l'entité Topic enregistrée.
     */
     Topic saveTopic(Topic topic);

    /**
     * Méthode pour créer un nouveau théme.
     * Prend en entrée un DTO de création de théme et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de théme.
     */
     TopicDtoReponseMessage createTopic(TopicDtoCreate topicDto, Principal principal);

    /**
     * Méthode pour récupérer toutes les topics et les convertir en DTO.
     * Retourne une réponse contenant une liste de DTO TopicDto.
     */

     TopicDtoGetAll getAllTopics();


    /**
     * Méthode pour aimer un topic.
     * @param userEmail
     * @param topicId
     * @return
     */
     ResponseEntity<String> likeTopic(String userEmail, Integer topicId);


    /**
     *  Méthode pour ne pas aimer un topic.
     * @param userEmail
     * @param topicId
     * @return
     */
     ResponseEntity<String> unlikeTopic(String userEmail, Integer topicId);
}

