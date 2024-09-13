package com.openclassrooms.mddapi.Dtos.PostDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object pour les messages de réponse des requêtes POST
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PostDtoResponseMessage {

    private String message;

}
