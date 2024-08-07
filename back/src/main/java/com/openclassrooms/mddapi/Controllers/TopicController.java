package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoCreate;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoGetAll;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoReponseMessage;
import com.openclassrooms.mddapi.Services.Interfaces.TopicService;
import com.openclassrooms.mddapi.exeptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class TopicController {

    private final TopicService topicService;
    private static final Logger log = LoggerFactory.getLogger(TopicController.class);

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

/**
     * Endpoint pour créer un nouveau Topic.
     * Prend en entrée un DTO de création du Topic et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Topic.
     */
    @PostMapping("/topics")
    public TopicDtoReponseMessage createTopic(@Valid @ModelAttribute TopicDtoCreate topicDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new ValidationException("Validation errors: " + errors);
        }
        return topicService.createTopic(topicDto, principal);
    }

/**
     * Endpoint pour récupérer tous les Topics.
     * @return la liste de tous les Topics
     */
    @GetMapping("/topics")
    public TopicDtoGetAll getAllTopics() {
        return topicService.getAllTopics();
    }

/**
     * Endpoint pour récupérer un Topic par son ID.
     * @param topicId l'ID du Topic à récupérer
     * @param principal l'utilisateur actuellement connecté
     * @return le Topic correspondant à l'ID
     */
    @PostMapping("/topics/{topicId}/like")
    public String likeTopic(@PathVariable Integer topicId, Principal principal) {
        return topicService.likeTopic(principal.getName(), topicId).getBody();
    }

/**
     * Endpoint pour récupérer un Topic par son ID.
     * @param topicId l'ID du Topic à récupérer
     * @param principal l'utilisateur actuellement connecté
     * @return le Topic correspondant à l'ID
     */
    @PostMapping("/topics/{topicId}/unlike")
    public String unlikeTopic(@PathVariable Integer topicId, Principal principal) {
        return topicService.unlikeTopic(principal.getName(), topicId).getBody();
    }
}