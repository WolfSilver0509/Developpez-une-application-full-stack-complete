package com.openclassrooms.mddapi.Dtos.UserDto;


import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DtoConvert {

    public UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getSubscribedTopics().stream()
                        .map(this::convertToTopicDto)
                        .collect(Collectors.toSet())

        );
    }

    public TopicDto convertToTopicDto(Topic topic) {
        return new TopicDto(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreated_at(),
                topic.getUpdated_at(),
                topic.getSubscribers().stream()
                        .map(this::convertToUserDto)
                        .collect(Collectors.toSet())
        );
    }
}