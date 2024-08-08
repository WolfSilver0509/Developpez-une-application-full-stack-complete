package com.openclassrooms.mddapi.Dtos.UserDto;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    @Setter
    private String jwtToken;
    private List<TopicDto> topics;


//    public <R> UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt, R collect) {
//    }


    public UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt, List<TopicDto> topics) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.topics = topics;
    }

}
