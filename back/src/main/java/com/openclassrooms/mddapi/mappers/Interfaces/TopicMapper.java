package com.openclassrooms.mddapi.mappers.Interfaces;

// Classe de service responsable de la gestion des thémes.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des thémes.

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;

import com.openclassrooms.mddapi.Models.Topic;



public interface TopicMapper {


    /*
     * Méthode pour convertir une entité Topic en DTO.
     * Prend en entrée une entité Topic.
     * Retourne un DTO TopicDto.
     */
     TopicDto convertToTopicDto(Topic topic);


}

