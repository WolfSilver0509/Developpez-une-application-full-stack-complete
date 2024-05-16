package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.TopicDto;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.HashMap;
//import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
//import java.util.Optional;
//import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class TopicController {

    private static final Logger log = LoggerFactory.getLogger(TopicController.class);


    @Autowired
    private TopicService topicService;


    /* Créer un nouveau théme */
    @PostMapping("/topics")
    public ResponseEntity<Map<String, String>> createTopic(@ModelAttribute TopicDto topicDto) {
        // Créer un théme
        Topic topic = new Topic(
                topicDto.getTitle(),
                topicDto.getDescription()
        );
        //Sauvegarder en base les nouvelles données
        topicService.saveTopic(topic);

        // Réponse de réussite
        Map<String, String> response = new HashMap<>();
        response.put("message", "Topic created !");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

        /* Obtenir un théme par son ID */
    @GetMapping("/topics/{id}")
    public ResponseEntity<TopicDto> getRentalById(@PathVariable Integer id) {
        Optional<Topic> rentalOptional = topicService.findById(id);
        if (rentalOptional.isPresent()) {
            Topic topic = rentalOptional.get();
            TopicDto topicDto = convertToTopicDto(topic);
            return ResponseEntity.ok(topicDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

        // Méthode pour convertir un objet Topic en TopicDto
    private TopicDto convertToTopicDto(Topic topic) {
        return new TopicDto(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreated_at(),
                topic.getUpdated_at()

        );
    }

    /* Obtenir toutes les locations */
    @GetMapping("/topics")
    public ResponseEntity<Map<String, List<TopicDto>>> getAllRentals() {
        // Récupérer touts les thémes
        List<Topic> rentals = topicService.findAllTopics();

        // Convertir les thémes en objets TopicDto
        List<TopicDto> rentalDtos = rentals.stream()
                .map(this::convertToTopicDto)
                .collect(Collectors.toList());

        // Créer un objet Map pour envelopper les thémes avec une clée topics
        Map<String, List<TopicDto>> response = new HashMap<>();
        response.put("topics", rentalDtos);

        return ResponseEntity.ok(response);
    }

//
//    // Méthode pour convertir un objet Topic en RentalDto
//    private TopicDto convertToRentalDto(Topic topic) {
//        return new TopicDto(
//                topic.getId(),
//                topic.getTitle(),
//                topic.getDescription(),
//                topic.getCreated_at(),
//                topic.getUpdated_at(),
//
//        );
//    }

//    /* Obtenir une location par son ID */
//    @GetMapping("/rentals/{id}")
//    public ResponseEntity<TopicDto> getRentalById(@PathVariable Integer id) {
//        Optional<Topic> rentalOptional = topicService.findById(id);
//        if (rentalOptional.isPresent()) {
//            Topic topic = rentalOptional.get();
//            TopicDto topicDto = convertToRentalDto(topic);
//            return ResponseEntity.ok(topicDto);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    
   
}
