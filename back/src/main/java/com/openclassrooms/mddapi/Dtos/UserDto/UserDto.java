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

}
