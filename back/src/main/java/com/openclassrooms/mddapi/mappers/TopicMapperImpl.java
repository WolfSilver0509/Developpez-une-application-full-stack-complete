package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.mappers.Interfaces.TopicMapper;
import org.springframework.stereotype.Service;

@Service
public class TopicMapperImpl implements TopicMapper {



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

}
