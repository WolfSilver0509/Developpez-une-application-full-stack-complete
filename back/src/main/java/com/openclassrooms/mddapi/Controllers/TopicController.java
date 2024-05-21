package com.openclassrooms.mddapi.Controllers;

// Ce package contient les contrôleurs pour la gestion des Topic

import com.openclassrooms.mddapi.Dtos.RentalDto.TopicDtoCreate;
import com.openclassrooms.mddapi.Dtos.RentalDto.TopicDtoReponseMessage;
import com.openclassrooms.mddapi.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("api") // Indique le chemin de base pour les requêtes HTTP
public class TopicController {

    @Autowired // Injection de dépendance pour TopicService
    private TopicService topicService;

    /*
     * Point de terminaison pour créer un nouveau théme.
     * Prend en entrée un DTO de création du théme et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de théme.
     */
    @PostMapping("/topics")
    public ResponseEntity<TopicDtoReponseMessage> createTopic(@ModelAttribute TopicDtoCreate topicDto, Principal principal) {
        TopicDtoReponseMessage responseMessage = topicService.createTopic(topicDto, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
