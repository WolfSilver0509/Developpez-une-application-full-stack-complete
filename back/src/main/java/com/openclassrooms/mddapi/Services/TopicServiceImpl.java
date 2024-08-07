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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final  UserRepository userRepository;

    public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findById(Integer id) {
        return topicRepository.findById(id);
    }

    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

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

    @Override
    public TopicDtoGetAll getAllTopics() {
        List<Topic> topics = findAllTopics();
        List<TopicDto> topicDtos = topics.stream()
                .map(this::convertToTopicDto)
                .collect(Collectors.toList());
        return (new TopicDtoGetAll(topicDtos));
    }

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
