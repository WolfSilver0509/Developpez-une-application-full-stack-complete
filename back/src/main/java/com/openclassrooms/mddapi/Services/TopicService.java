package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import com.openclassrooms.mddapi.Models.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public void saveTopicNow(Topic topic) {
        topicRepository.save(topic);
    }

    /* Méthode pour récupérer tous les thémes */
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    /* Méthode pour récupérer une théme par son identifiant */
    public Optional<Topic> findById(Integer id) {
        return topicRepository.findById(id);
    }

    /* Méthode pour sauvegarder un theme */
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic); // Enregistre l'objet de théme
    }

}
