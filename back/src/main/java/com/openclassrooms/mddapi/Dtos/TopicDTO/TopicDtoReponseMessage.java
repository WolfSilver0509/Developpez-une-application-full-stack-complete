package com.openclassrooms.mddapi.Dtos.TopicDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object pour les messages de réponse des TopicDto
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class TopicDtoReponseMessage {

    private String message;

}
