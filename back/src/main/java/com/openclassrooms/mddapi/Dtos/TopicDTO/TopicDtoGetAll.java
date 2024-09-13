package com.openclassrooms.mddapi.Dtos.TopicDTO;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Dto utilisé pour récupérer la liste des thémes
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TopicDtoGetAll {
    private List<TopicDto> topics;
}
