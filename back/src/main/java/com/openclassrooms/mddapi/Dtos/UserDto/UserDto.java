package com.openclassrooms.mddapi.Dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private String jwtToken;

    public <R> UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt, R collect) {
    }


    public UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
