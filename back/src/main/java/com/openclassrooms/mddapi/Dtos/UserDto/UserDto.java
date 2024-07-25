package com.openclassrooms.mddapi.Dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;


    @NotEmpty
    private Set<TopicDto> subscribedTopics;


}
