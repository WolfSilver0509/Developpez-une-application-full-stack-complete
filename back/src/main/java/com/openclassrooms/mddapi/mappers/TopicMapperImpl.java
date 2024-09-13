package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.mappers.Interfaces.TopicMapper;
import org.springframework.stereotype.Service;

/**
 * Classe de service responsable de la gestion des thémes.
 * Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des thémes.
 */
@Service
public class TopicMapperImpl implements TopicMapper {


    /**
     * Méthode pour convertir une entité Topic en DTO.
     * @param topic
     * @return
     */
    @Override
    public TopicDto convertToTopicDto(Topic topic) {
        return new TopicDto(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreated_at(),
                topic.getUpdated_at()
        );
    }

}
