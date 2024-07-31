package com.openclassrooms.mddapi.Services;

// Classe de service responsable de la gestion des thémes.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des thémes.

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoCreate;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoGetAll;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoReponseMessage;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Dtos.UserDto.DtoConvert;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service // Indique que cette classe est un composant de service dans Spring
public class TopicService {

    @Value("${app.base-url}") // Injection de la valeur de l'URL de base à partir des propriétés de l'application
    private String baseUrl;

    @Autowired // Injection de dépendance pour TopicRepository
    private TopicRepository topicRepository;

    @Autowired // Injection de dépendance pour userRepository
    private UserRepository userRepository;



    @Autowired
    private DtoConvert dtoConvert;

    /*
     * Méthode pour récupérer tous les thémes.
     * Retourne une liste de toutes les entités Topic.
     */
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    /*
     * Méthode pour récupérer un théme par son ID.
     * Prend en entrée l'ID du théme.
     * Retourne un Optional contenant l'entité Topic si elle est trouvée.
     */
    public Optional<Topic> findById(Integer id) {
        return topicRepository.findById(id);
    }

    /*
     * Méthode pour enregistrer un théme.
     * Prend en entrée une entité Topic et la sauvegarde dans la base de données.
     * Retourne l'entité Topic enregistrée.
     */
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    /*
     * Méthode pour créer un nouveau théme.
     * Prend en entrée un DTO de création de théme et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de théme.
     */
    public TopicDtoReponseMessage createTopic(TopicDtoCreate topicDto, Principal principal) {
        User currentUser = (User) ((Authentication) principal).getPrincipal(); // Récupération de l'utilisateur actuel
        Topic topic = new Topic(
                topicDto.getTitle(),
                topicDto.getDescription()
        );
        saveTopic(topic);
        return new TopicDtoReponseMessage("Topic created!");
    }

    /*
     * Méthode pour récupérer toutes les topics et les convertir en DTO.
     * Retourne une réponse contenant une liste de DTO TopicDto.
     */

    public TopicDtoGetAll getAllTopics(){
        List<Topic> topics = findAllTopics();
        List<TopicDto> topicDtos = topics.stream()
                .map(dtoConvert::convertToTopicDto)
                .collect(Collectors.toList());
        return (new TopicDtoGetAll(topicDtos));
    }

    /*
     * Méthode pour convertir une entité Topic en DTO.
     * Prend en entrée une entité Topic.
     * Retourne un DTO TopicDto.
     */
    private TopicDto convertToTopicDto(Topic topic) {
        return new TopicDto(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreated_at(),
                topic.getUpdated_at()
        );
    }

    @Transactional
    public ResponseEntity<String> likeTopic(String userEmail, Integer topicId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow();
        user.getTopics().add(topic);
        userRepository.save(user);
        return ResponseEntity.ok("Topic liked successfully!");
    }

    @Transactional
    public ResponseEntity<String> unlikeTopic(String userEmail, Integer topicId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow();
        user.getTopics().remove(topic);
        userRepository.save(user);
        return ResponseEntity.ok("Topic unliked successfully!");
    }
}
