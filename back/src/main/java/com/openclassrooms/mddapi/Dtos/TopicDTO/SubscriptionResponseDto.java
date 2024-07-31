package com.openclassrooms.mddapi.Dtos.TopicDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriptionResponseDto {
    private Integer userId;
    private Integer topicId;
    private String message;

}
