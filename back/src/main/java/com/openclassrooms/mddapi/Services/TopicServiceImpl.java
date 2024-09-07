package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoCreate;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoGetAll;
import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDtoReponseMessage;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import com.openclassrooms.mddapi.Services.Interfaces.TopicService;
import com.openclassrooms.mddapi.mappers.Interfaces.TopicMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service pour les thémes.
 */
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final  UserRepository userRepository;
    private final TopicMapper topicMapper;

    public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.topicMapper = topicMapper;
    }

    /**
     * Méthode pour récupérer tous les thémes.
     * Retourne une liste de toutes les entités Topic.
     */
    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    /**
     * Méthode pour récupérer un théme par son ID.
     * Prend en entrée l'ID du théme.
     * Retourne un Optional contenant l'entité Topic si elle est trouvée.
     */
    @Override
    public Optional<Topic> findById(Integer id) {
        return topicRepository.findById(id);
    }

    /**
     * Méthode pour enregistrer un théme.
     * Prend en entrée une entité Topic et la sauvegarde dans la base de données.
     * Retourne l'entité Topic enregistrée.
     */
    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    /**
     * Méthode pour créer un nouveau théme.
     * Prend en entrée un DTO de création de théme et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de théme.
     */
    @Override
    public TopicDtoReponseMessage createTopic(TopicDtoCreate topicDto, Principal principal) {
        User currentUser = (User) ((Authentication) principal).getPrincipal(); // Récupération de l'utilisateur actuel
        Topic topic = new Topic(
                topicDto.getTitle(),
                topicDto.getDescription()
        );
        saveTopic(topic);
        return new TopicDtoReponseMessage("Topic created!");
    }

    /**
     * Méthode pour récupérer toutes les topics et les convertir en DTO.
     * Retourne une réponse contenant une liste de DTO TopicDto.
     */
    @Override
    public TopicDtoGetAll getAllTopics() {
        List<Topic> topics = findAllTopics();
        List<TopicDto> topicDtos = topics.stream()
                .map(topicMapper::convertToTopicDto)
                .collect(Collectors.toList());
        return (new TopicDtoGetAll(topicDtos));
    }

/**
     * Méthode pour aimer un topic.
     * @param userEmail
     * @param topicId
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity<String> likeTopic(String userEmail, Integer topicId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        // Vérifier si l'utilisateur a déjà liké ce topic
        if (user.getTopics().contains(topic)) {
            return ResponseEntity.badRequest().body("You have already liked this topic!");
        }

        // Ajouter le topic à la liste des topics likés
        user.getTopics().add(topic);
        userRepository.save(user);

        return ResponseEntity.ok("Topic like with Sucess !");
    }

    /**
     *  Méthode pour ne pas aimer un topic.
     * @param userEmail
     * @param topicId
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity<String> unlikeTopic(String userEmail, Integer topicId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        // Vérifier si l'utilisateur a déjà liké ce topic
        if (!user.getTopics().contains(topic)) {
            return ResponseEntity.badRequest().body("You haven't liked this topic yet !");
        }

        // Retirer le topic de la liste des topics likés
        user.getTopics().remove(topic);
        userRepository.save(user);

        return ResponseEntity.ok("Topic dislike with sucess !");
    }

}
